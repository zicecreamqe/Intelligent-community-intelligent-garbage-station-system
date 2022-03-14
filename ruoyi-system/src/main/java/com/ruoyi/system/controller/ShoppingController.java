package com.ruoyi.system.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.IotDeviceSet;
import com.ruoyi.system.domain.Shopping;
import com.ruoyi.system.service.ShoppingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "商品配置", tags = "商品配置")
@RestController
@RequestMapping("/system/shopping")
public class ShoppingController extends BaseController {

    @Autowired
    private ShoppingService shoppingService;

    /**
     * 查询商品列表
     */
    @ApiOperation(value = "查询设备配置列表", notes = "查询设备配置列表")
    @PreAuthorize("@ss.hasPermi('system:shopping:list')")
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<Shopping> list = shoppingService.selectShoppingList();
        System.out.println("-----"+list);
        return getDataTable(list);
    }
}
