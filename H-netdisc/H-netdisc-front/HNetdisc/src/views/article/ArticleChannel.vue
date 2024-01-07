<script setup>
import PageContainer from "@/views/article/component/page-container.vue";
import {artDelChannelService, artGetChannelsService} from "@/api/article";
import {onMounted,ref} from 'vue'
import {Delete, Edit} from "@element-plus/icons-vue";
import ChannelEdit from "@/views/article/component/ChannelEdit.vue";

const channelList = ref([])

const loading = ref(false)
const getChannelLit = async ()=>{
  loading.value = true
  const res = await artGetChannelsService()
  channelList.value = res.data.data
  loading.value = false
}
const onEditChannel = (row) => {
  console.log(row)
  dialog.value.open(row)
}

//ref对象绑定 标签组件 ，从而获得里面暴露的open方法
const dialog = ref()
const onAddChannel = () => {
  dialog.value.open({})
}

const onSuccess = () => {
  getChannelLit()
}


//文章删除
const onDelChannel = async (row) => {
  console.log(row)
  await ElMessageBox.confirm('你确认删除该分类信息吗？', '温馨提示', {
    type: 'warning',
    confirmButtonText: '确认',
    cancelButtonText: '取消'
  })
  await artDelChannelService(row.id)
  ElMessage({ type: 'success', message: '删除成功' })
  getChannelLit()
}


onMounted(()=>{
  console.log('开始发送请求')
  getChannelLit()
})
</script>

<template>
  <page-container title="文章分类">
    <template #extra>
      <el-button type="primary" @click="onAddChannel">添加分类</el-button>
    </template>
    <el-form inline>
      <el-form-item label="文章分类：">
        <el-select>
          <el-option label="新闻" value="111"></el-option>
          <el-option label="体育" value="222"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发布状态：">
        <el-select>
          <el-option label="已发布" value="已发布"></el-option>
          <el-option label="草稿" value="草稿"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary">搜索</el-button>
        <el-button>重置</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="channelList" v-loading="loading" style="width: 100%">
      <el-table-column label="序号" width="100" type="index"> </el-table-column>
      <el-table-column label="分类名称" prop="cate_name"></el-table-column>
      <el-table-column label="分类别名" prop="cate_alias"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button
              :icon="Edit"
              circle
              plain
              type="primary"
              @click="onEditChannel(row)"
          ></el-button>
          <el-button
              :icon="Delete"
              circle
              plain
              type="danger"
              @click="onDelChannel(row)"
          ></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据" />
      </template>
    </el-table>
    <channel-edit ref="dialog" @success="onSuccess">
    </channel-edit>
  </page-container>
</template>

<style lang="scss" scoped>
.page-container {
  min-height: 100%;
  box-sizing: border-box;
  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}
</style>