import request from '@/utils/request'

// 指定机构的房间列表及入住情况
export function fetchList(flag) {
    return request({
        url: '/room/list/' + flag,
        method: 'get',
    })
}

export function addRoom(params) {
    return request({
        url: '/room/add',
        method: 'post',
        params: params
    })
}

export function fetchOutRoomList() {
    return request({
        url: '/room/listcommunity',
        method: 'get',
    })
}

export function fetchRoomDetail(roomid) {
    return request({
        url: '/room/' + roomid + '/query',
        method:'get',
    })

}