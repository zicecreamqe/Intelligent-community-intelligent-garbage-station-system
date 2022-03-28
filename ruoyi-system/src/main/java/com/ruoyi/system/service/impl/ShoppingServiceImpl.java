package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.IotDevice;
import com.ruoyi.system.domain.Shopping;
import com.ruoyi.system.mapper.ShoppingMapper;
import com.ruoyi.system.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingMapper shoppingMapper;

    @Override
    public List<Shopping> selectShoppingList(Shopping shopping) {
        System.out.println("---"+shoppingMapper.selectShoppingList(shopping));
        return shoppingMapper.selectShoppingList(shopping);
    }

    @Override
    public Shopping selectShoppingById(Long shoppingId) {
        return shoppingMapper.selectShoppingById(shoppingId);
    }

    @Override
    public int updateShopping(Shopping shopping) {
        return shoppingMapper.updateShopping(shopping);
    }

    @Override
    public Shopping selectShoppingByNumber(String shoppingNumber) {
        return shoppingMapper.selectShoppingByNumber(shoppingNumber);
    }

    @Override
    public int insertShopping(Shopping shopping) {
        shopping.setShoppingCreateTime(DateUtils.getNowDate());
        return shoppingMapper.insertShopping(shopping);
    }

    @Override
    public int deleteShoppingById(Long shoppingId) {
        return shoppingMapper.deleteShoppingById(shoppingId);
    }
}
