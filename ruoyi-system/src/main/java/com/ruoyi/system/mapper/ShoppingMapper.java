package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Shopping;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingMapper {

    public List<Shopping> selectShoppingList();
}
