<script setup>
import {User, Lock, Check, Message} from '@element-plus/icons-vue'
import {ref, watch, onMounted} from 'vue'
import {userLoginService, userRegisterService} from "@/api/user";
import {useRouter} from "vue-router";
import {useUserStore} from "@/stores";
import {getBase64VerCode, sendEmailVerCode} from "@/api/vercode";

const isRegister = ref(1)//0-注册，1-登录，2-找回密码
const formModel = ref({
  userName: '',
  password: '',
  checkCode: '',
  type: '',
  emailCode:''
})
const rules = {
  userName: [
    {required: true, message: '请输入昵称', trigger: 'blur'},
    {min: 3, max: 10, message: '昵称必须是5-10位的字符', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {
      pattern: /^\S{6,15}$/,
      message: '密码必须是6-15位的非空字符',
      trigger: 'blur'
    }
  ],
  repassword: [
    {required: true, message: '请再次输入密码', trigger: 'blur'},
    {
      pattern: /^\S{6,15}$/,
      message: '密码必须是6-15的非空字符',
      trigger: 'blur'
    },
    {
      validator: (rule, value, callback) => {
        if (value !== formModel.value.password) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ],
  email: [
    {required: true, message: '请输入邮箱', trigger: 'blur'},
    {type: 'email', message: '邮箱格式不正确', trigger: 'blur'}
  ],
  checkCode: [
    {required: true, message: '请输入验证码', trigger: 'blur'},
    {
      pattern: /^\S{6}$/,
      message: '验证码必须是6位的非空字符',
      trigger: 'blur'
    }
  ],

}


const form = ref()
const register = async () => {
  // await form.value.validator()
  console.log('开始注册请求')
  await userRegisterService(formModel.value)
  ElMessage.success('注册成功')
  // 切换到登录
  isRegister.value = 1
}
watch(isRegister, () => {
  // console.log('register changes')
  formModel.value = {
    userName: '',
    password: '',
    checkCode: '',
    type: '',
    emailCode:''
  }
  getVerCode()
})
// 获取图形验证码
const vercodeBase64 = ref('')
const getVerCode = async () => {
  const res = await getBase64VerCode()
  vercodeBase64.value = res.data.body
  // console.log(res)
}

//获取邮箱验证码
const sendEmailVercode = () => {
  if (isRegister.value==2){
    formModel.value.type=1
  }else{
    formModel.value.type=0
  }
  //todo 改造成异步
  console.log('发送邮箱验证码')
  // new Promise((resolve, reject) => {
  //
  // })
  sendEmailVerCode(formModel.value).then(res => {
    ElMessage.success('发送成功')
    console.log(res)
  })
}

const router = useRouter()
const userStore = useUserStore()


const login = async () => {
  // await form.value.validator()
  console.log('开始登录')
  const res = await userLoginService(formModel.value)
  userStore.setToken(res.data.token)
  ElMessage.success('登陆成功')
  router.push('/')
}

onMounted(() => {
  console.log('获取图形验证码')
  getVerCode()
})

</script>

<template>
  <el-row class="login-page">
    <el-col :span="12" class="bg"></el-col>
    <el-col :span="6" :offset="3" class="form">
      <!--      注册表单-->
      <el-form :rules="rules" ref="form" size="large" autocomplete="off" v-if="isRegister==0" :model="formModel">
        <el-form-item>
          <h1>注册</h1>
        </el-form-item>
        <el-form-item prop="email">
          <el-input
              v-model="formModel.email"
              :prefix-icon="Message"
              placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item prop="checkCode">
          <el-row>
            <el-col :span="22">
              <el-input
                  v-model="formModel.checkCode"
                  name="vercode"
                  :prefix-icon="Check"
                  placeholder="请输入验证码"
              ></el-input>
            </el-col>
            <el-col :span="1">
              <img :src="vercodeBase64" @click="getVerCode"/>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item prop="checkCode">
          <el-row>
            <el-col :span="22">
              <el-input
                  v-model="formModel.emailCode"
                  name="emailvercode"
                  :prefix-icon="Check"
                  placeholder="请输入邮箱验证码"
              ></el-input>
            </el-col>
            <el-col :span="1">
              <el-button type="primary" @click="sendEmailVercode">获取验证码</el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item prop="userName">
          <el-input
              v-model="formModel.userName"
              :prefix-icon="User"
              placeholder="请输入昵称"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              v-model="formModel.password"
              :prefix-icon="Lock"
              type="password"
              placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item prop="repassword">
          <el-input
              v-model="formModel.repassword"
              :prefix-icon="Lock"
              type="password"
              placeholder="请输入再次密码"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
              @click="register"
              class="button" type="primary" auto-insert-space>
            注册
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = 1">
            ← 返回
          </el-link>
        </el-form-item>
      </el-form>

      <!--      登录表单-->
      <el-form :model="formModel" :rules="rules" ref="form" size="large" autocomplete="off" v-if="isRegister==1">
        <el-form-item>
          <h1>登录</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input v-model="formModel.username" :prefix-icon="User" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              v-model="formModel.password"
              name="password"
              :prefix-icon="Lock"
              type="password"
              placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item prop="checkCode">
          <el-row>
            <el-col :span="22">
              <el-input
                  v-model="formModel.checkCode"
                  name="vercode"
                  :prefix-icon="Check"
                  placeholder="请输入验证码"
              ></el-input>
            </el-col>
            <el-col :span="1">
              <img :src="vercodeBase64" @click="getVerCode"/>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item class="flex">
          <div class="flex">
            <el-checkbox>记住我</el-checkbox>
            <el-link type="primary" :underline="false" @click="evt => isRegister=2">忘记密码？</el-link>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button class="button" type="primary" auto-insert-space @click="login"
          >登录
          </el-button
          >
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = 0">
            注册 →
          </el-link>
        </el-form-item>
      </el-form>

      <!--      忘记表单-->
      <el-form :rules="rules" ref="form" size="large" autocomplete="off" v-if="isRegister==2" :model="formModel">
        <el-form-item>
          <h1>找回密码</h1>
        </el-form-item>
        <el-form-item prop="email">
          <el-input
              v-model="formModel.email"
              :prefix-icon="Message"
              placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item prop="checkCode">
          <el-row>
            <el-col :span="22">
              <el-input
                  v-model="formModel.checkCode"
                  name="vercode"
                  :prefix-icon="Check"
                  placeholder="请输入验证码"
              ></el-input>
            </el-col>
            <el-col :span="1">
              <img :src="vercodeBase64" @click="getVerCode"/>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item prop="checkCode">
          <el-row>
            <el-col :span="22">
              <el-input
                  v-model="formModel.emailCode"
                  name="emailvercode"
                  :prefix-icon="Check"
                  placeholder="请输入邮箱验证码"
              ></el-input>
            </el-col>
            <el-col :span="1">
              <el-button type="primary" @click="sendEmailVercode">获取验证码</el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              v-model="formModel.password"
              :prefix-icon="Lock"
              type="password"
              placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item prop="repassword">
          <el-input
              v-model="formModel.repassword"
              :prefix-icon="Lock"
              type="password"
              placeholder="请输入再次密码"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
              @click="register"
              class="button" type="primary" auto-insert-space>
            注册
          </el-button>
        </el-form-item>
        <el-form-item class="flex">
          <el-link type="info" :underline="false" @click="isRegister = 1">
            ← 返回
          </el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
.login-page {
  height: 100vh;
  background-color: #fff;

  .bg {
    background: url('@/assets/logo2.png') no-repeat 60% center / 240px auto,
    url('@/assets/login_bg.jpg') no-repeat center / cover;
    //border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;
    user-select: none;

    .title {
      margin: 0 auto;
    }

    .button {
      width: 100%;
    }

    .flex {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>