import request from '@/utils/request'

// 获取某一养老院的全部设备列表
export function changeBind(params) {
    return request({
        url: '/bed/bind',
        method: 'post',
        params:params,
    })
}
