import request from '@/utils/request'

// 获取某一养老院的全部护工列表
export function fetchList() {
    return request({
        url: '/worker/list',
        method: 'get',
    })
}

// 上传护工
export function addWorker(params) {
    return request({
        url: '/worker/add',
        method: 'post',
        params: params
    })
}

// 更新护工信息
export function updateWorker(id, params) {
    return request({
        url: '/worker/' + id + '/update',
        method: 'post',
        params: params
    })
}

// 删除护工信息
export function deleteWorker(id) {
    return request({
        url: '/worker/' + id + '/delete',
        method: 'post',
    })
}

// 查询护工信息
export function queryWorker(id) {
    return request({
        url: '/worker/' + id + '/query',
        method: 'get',
    })
}

// 查询护工监管的房间列表
export function roomList(id) {
    return request({
        url: '/worker/' + id + '/roomlist',
        method: 'get',
    })
}

// 更新监护房间
export function assignRoom(id, roomID, flag) {
    return request({
        url: '/worker/' + id + '/assign/' + roomID,
        method: 'post',
        data: {
            flag
        }
    })
}