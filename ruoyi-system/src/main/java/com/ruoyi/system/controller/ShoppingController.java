package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.IotDevice;
import com.ruoyi.system.domain.IotDeviceSet;
import com.ruoyi.system.domain.Shopping;
import com.ruoyi.system.service.ShoppingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    public TableDataInfo list(Shopping shopping) {
        startPage();
        System.out.println("看一下后台接收"+shopping);
        List<Shopping> list = shoppingService.selectShoppingList(shopping);
        return getDataTable(list);
    }

    /**
     * 获取商品详细信息
     */
    @ApiOperation(value = "获取商品详情", notes = "获取商品详情")
    @PreAuthorize("@ss.hasPermi('system:shopping:query')")
    @GetMapping(value = "/{shoppingId}")
    public AjaxResult getInfo(@PathVariable("shoppingId") Long shoppingId) {
        System.out.println("1212---"+shoppingService.selectShoppingById(shoppingId));
        return AjaxResult.success(shoppingService.selectShoppingById(shoppingId));
    }

    /**
     * 修改商品信息
     */
    @ApiOperation(value = "修改商品信息", notes = "修改商品信息")
    @PreAuthorize("@ss.hasPermi('system:shopping:edit')")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Shopping shopping) {
        return toAjax(shoppingService.updateShopping(shopping));
    }

    /**
     * 新增商品
     */
    @ApiOperation(value = "新增商品", notes = "新增商品")
    @PreAuthorize("@ss.hasPermi('system:shopping:add')")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Shopping shopping) {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        shopping.setShoppingCreateBy(user.getUser().getUserName());
        shopping.setState(1);
        Shopping HaveShopping = shoppingService.selectShoppingByNumber(shopping.getShoppingNumber());
        if (HaveShopping != null) {
            return AjaxResult.error("商品编号已存在，请重新填写");
        }
        return toAjax(shoppingService.insertShopping(shopping));
    }

    /**
     * 删除商品
     */
    @ApiOperation(value = "删除商品", notes = "删除商品")
    @PreAuthorize("@ss.hasPermi('system:shopping:remove')")
    @Log(title = "商品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{shoppingId}")
    public AjaxResult remove(@PathVariable Long shoppingId) {
        return toAjax(shoppingService.deleteShoppingById(shoppingId));
    }
}
