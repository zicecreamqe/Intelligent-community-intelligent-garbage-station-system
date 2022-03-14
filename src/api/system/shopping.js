import request from '@/utils/request'

// 查询商品列表
export function shoppingList() {
  return request({
    url: '/system/shopping/list',
    method: 'get'
  })
}
