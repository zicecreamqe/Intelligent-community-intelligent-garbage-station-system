import request from '@/utils/request'

// 查询商品列表
export function shoppingList(query) {
  return request({
    url: '/system/shopping/list',
    method: 'get',
    params: query
  })
}

// 查询商品详细
export function getShopping(shoppingId) {
  return request({
    url: '/system/shopping/' + shoppingId,
    method: 'get'
  })
}

// 修改商品
export function updateShopping(data) {
  return request({
    url: '/system/shopping',
    method: 'put',
    data: data
  })
}

// 新增商品
export function addShopping(data) {
  return request({
    url: '/system/shopping',
    method: 'post',
    data: data
  })
}

// 删除商品
export function deleteShopping(shoppingId) {
  return request({
    url: '/system/shopping/' + shoppingId,
    method: 'delete'
  })
}
