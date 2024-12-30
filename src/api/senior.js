import request from '@/utils/request'

// 获取某一养老院的全部老人列表,flag=0表示获取院内的老人列表，1表示获取院外接入社区的老人列表
export function fetchList(flag) {
    return request({
        url: '/senior/list/' + flag,
        method: 'get',

    })
}

// 新增老人
export function addSenior(params) {
    if (params.flag == 0) {
        return request({
            url: '/senior/addincarehome',
            method: 'post',
            params: params,
        })
    }
    else {
        return request({
            url: '/senior/addincommunity',
            method: 'post',
            params: params,
        })
    }

}

export function updateSenior(id, params) {
    return request({
        url: '/senior/' + id + '/update',
        method: 'post',
        params: params,
    })
}

// 换床
export function bindBed(seniorID, bedID) {
    return request({
        url: '/senior/' + seniorID + '/bind/' + bedID,
        method: 'post',
    })
}

// 换床
export function getSeniorDetail(seniorID) {
    return request({
        url: '/senior/' + seniorID + '/detail',
        method: 'get',
    })
}


export function getLogData(id, date) {
    return request({
        url: '/senior/' + id + '/log/' + date,
        method: 'get'
    })
}

export function getSeniorEvent(id) {
    return request({
        url: '/senior/' + id + '/events',
        method: 'get',
    })

}

export function fecthSeniorLog(id) {
    return request({
        url: '/senior/' + id + '/log',
        method: 'get'
    })
}

