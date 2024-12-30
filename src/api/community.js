import request from '@/utils/request'

// 指定机构的房间列表及入住情况
export function fetchList() {
    return request({
        url: '/community/list',
        method: 'get',
    })
}

export function addCommunity(params) {
    return request({
        url: '/community/add',
        method: 'post',
        params: params,
    })
}