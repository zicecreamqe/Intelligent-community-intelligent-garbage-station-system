package com.ruoyi.system.service.impl;

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
    public List<Shopping> selectShoppingList() {
        System.out.println("---"+shoppingMapper.selectShoppingList());
        return shoppingMapper.selectShoppingList();
    }
}
