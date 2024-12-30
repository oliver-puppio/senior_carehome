import request from '@/utils/request'

// 查看实时监测数据列表；
export function fetchLiveData() {
    return request({
        url: '/record/live',
        method: 'get',
    })
}

// 检查实时监测数据列表；
export function checkLiveData() {
    return request({
        url: '/record/livecheck',
        method: 'get',
    })
}

export function fetchDeviceLive(deviceid) {
    return request({
        url: '/device/' + deviceid + '/live',
        method: 'get',
    })
}