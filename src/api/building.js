import request from '@/utils/request'

// 指定机构的房间列表及入住情况
export function fetchList() {
    return request({
        url: '/building/list',
        method: 'get',
    })
}

export function addBuilding(name) {
    return request({
        url: '/building/add',
        method: 'post',
        data: { buildingname: name, }
    })
}