import request from "@/utils/request"



export const artGetChannelsService = () => request.get('my/cate/list')

// 添加文章分类
export const artAddChannelService = (data) => request.post('/my/cate/add', data)
// 编辑文章分类
export const artEditChannelService = (data) => request.put('/my/cate/info', data)


// 删除文章分类
export const artDelChannelService = (id) =>
    request.delete('/my/cate/del', {
        params: { id }
    })

export const artGetListService = (params) =>
    request.get('/my/article/list', { params })

export const artPublishService = (data) =>
    request.post('/my/article/add', data)

export const artGetDetailService = (id) =>
    request.get('my/article/info', { params: { id } })

export const artEditService = (data) => request.put('my/article/info', data)

export const artDelService = (id) => request.delete('my/article/info', { params: { id } })