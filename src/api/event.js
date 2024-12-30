import request from '@/utils/request'

// 查看异常事件列表
export function fetchList() {
    return request({
        url: '/event/list',
        method: 'get',
    })
}

// 查看异常事件列表
export function fetchDoneList() {
    return request({
        url: '/event/list/finished',
        method: 'get',
    })
}

// 查看异常事件列表
export function fetchDoingList() {
    return request({
        url: '/event/list/unfinished',
        method: 'get',
    })
}

// 上传异常事件（新增）
export function addEvent(params) {
    return request({
        url: '/event/add',
        method: 'post',
        params: params
    })
}

// 
export function confirmEvent(eventid) {
    return request({
        url: '/event/' + eventid + '/confirm',
        method: 'post',
    })
}
