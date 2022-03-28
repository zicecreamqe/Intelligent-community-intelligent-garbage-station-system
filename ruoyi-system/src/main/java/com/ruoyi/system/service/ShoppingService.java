package com.ruoyi.system.service;

import com.ruoyi.system.domain.IotDevice;
import com.ruoyi.system.domain.Shopping;

import java.util.List;

public interface ShoppingService {
    /**
     * 查询所有商品
     * @return 商品集合
     */
    public List<Shopping> selectShoppingList(Shopping shopping);

    /**
     * 根据商品编号查询详细商品信息
     * @param shoppingId 商品编号
     * @return Shopping
     */
    Shopping selectShoppingById(Long shoppingId);

    /**
     * 根据传入id参数修改商品信息
     * @param shopping 封装好的商品信息
     * @return 影响行数
     */
    int updateShopping(Shopping shopping);

    /**
     * 根据商品编码查询详细商品信息
     * @param shoppingNumber 商品编号
     * @return Shopping
     */
    Shopping selectShoppingByNumber(String shoppingNumber);

    /**
     * 添加商品
     * @param shopping 商品参数
     * @return 影响行数
     */
    int insertShopping(Shopping shopping);

    /**
     * 根据商品id删除商品
     * @param shoppingId 商品id
     * @return 影响行数
     */
    int deleteShoppingById(Long shoppingId);
}
