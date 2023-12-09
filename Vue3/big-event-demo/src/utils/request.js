import axios from 'axios'
import {useUserStore} from '@/stores'
import {ElMessage} from "element-plus";
import router from "@/router";

const baseURL = 'http://big-event-vue-api-t.itheima.net'

const instance = axios.create({
    // TODO 1. 基础地址，超时时间
    baseURL,
    timeout: 10000
})

instance.interceptors.request.use(
    (config) => {
        // TODO 2. 携带token
        const useStore = useUserStore()
        if (useStore.token) {
            config.headers.Authorization = useStore.token
        }
        return config
    },
    (err) => Promise.reject(err)
)

instance.interceptors.response.use(
    (res) => {
        // TODO 3. 处理业务失败
        // TODO 4. 摘取核心响应数据
        if (res.data.code === 0) {

            return res
        }
        ElMessage({message: res.data.message || '服务异常', type: 'error'})
        return Promise.reject(res.data)
    },
    (err) => {
        ElMessage({message: err.response.data.message || '服务异常', type: 'error'})
        console.log(err)
        if (err.response?.status === 401) {
            router.push('/login')
        }
        return Promise.reject(err)
    }
)

export default instance
export {baseURL}