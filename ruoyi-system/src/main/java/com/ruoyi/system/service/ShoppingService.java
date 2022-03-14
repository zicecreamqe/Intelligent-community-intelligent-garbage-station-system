package com.ruoyi.system.service;

import com.ruoyi.system.domain.Shopping;

import java.util.List;

public interface ShoppingService {
    /**
     * 查询所有商品
     * @return 商品集合
     */
    public List<Shopping> selectShoppingList();
}
