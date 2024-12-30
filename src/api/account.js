import request from '@/utils/request'

export function update(params) {
  return request({
    url: '/admin/update',
    method: 'post',
    params
  })
}

export function query() {
    return request({
      url: '/admin/query',
      method: 'get',
    })
  }