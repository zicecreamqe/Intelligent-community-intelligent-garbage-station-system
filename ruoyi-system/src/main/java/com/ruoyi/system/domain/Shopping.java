package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class Shopping extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private Long shoppingId;

    /**
     * 商品编码
     */
    @Excel(name = "商品编码")
    private String shoppingNumber;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String shoppingName;

    /**
     * 创建者
     */
    @Excel(name = "创建者")
    private String shoppingCreateBy;

    /**
     * 积分
     */
    @Excel(name = "积分")
    private int shoppingIntegral;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 库存
     */
    @Excel(name = "库存")
    private int shoppingStock;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    private Date shoppingCreateTime;

    public Long getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(Long shoppingId) {
        this.shoppingId = shoppingId;
    }

    public String getShoppingNumber() {
        return shoppingNumber;
    }

    public void setShoppingNumber(String shoppingNumber) {
        this.shoppingNumber = shoppingNumber;
    }

    public String getShoppingName() {
        return shoppingName;
    }

    public void setShoppingName(String shoppingName) {
        this.shoppingName = shoppingName;
    }

    public String getShoppingCreateBy() {
        return shoppingCreateBy;
    }

    public void setShoppingCreateBy(String shoppingCreateBy) {
        this.shoppingCreateBy = shoppingCreateBy;
    }

    public int getShoppingIntegral() {
        return shoppingIntegral;
    }

    public void setShoppingIntegral(int shoppingIntegral) {
        this.shoppingIntegral = shoppingIntegral;
    }

    public int getShoppingStock() {
        return shoppingStock;
    }

    public void setShoppingStock(int shoppingStock) {
        this.shoppingStock = shoppingStock;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getShoppingCreateTime() {
        return shoppingCreateTime;
    }

    public void setShoppingCreateTime(Date shoppingCreateTime) {
        this.shoppingCreateTime = shoppingCreateTime;
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "shoppingId=" + shoppingId +
                ", shoppingNumber=" + shoppingNumber +
                ", shoppingName=" + shoppingName +
                ", shoppingCreateBy=" + shoppingCreateBy +
                ", shoppingIntegral=" + shoppingIntegral +
                ", remark=" + remark +
                ", shoppingCreateTime=" + shoppingCreateTime +
                '}';
    }
}
