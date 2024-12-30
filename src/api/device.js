import request from '@/utils/request'

// 获取某一养老院的全部设备列表
export function deviceList() {
    return request({
        url: '/device/list',
        method: 'get',
    })
}

// 添加设备
export function addDevice(id) {
    return request({
        url: '/device/add',
        method: 'post',
        data: {
            id
        }
    })
}

// 禁用设备,
export function ditchDevice(id, flag) {
    return request({
        url: '/device/' + id + '/ditch',
        method: 'post',
        data: {
            flag
        }
    })
}