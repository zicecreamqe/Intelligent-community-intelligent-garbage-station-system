package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 硬件传入参数
 */
public class TcpDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 设备编号
     */
    @Excel(name = "编号")
    private String deviceNum;

    /**
     * 设备温度
     */
    @Excel(name = "设备温度")
    private BigDecimal deviceTemperature;

    /**
     * 设备满度
     */
    @Excel(name = "满度")
    private Integer full;

    /**
     * 设备湿度
     */
    @Excel(name = "湿度")
    private BigDecimal deviceHumidity;

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public BigDecimal getDeviceTemperature() {
        return deviceTemperature;
    }

    public void setDeviceTemperature(BigDecimal deviceTemperature) {
        this.deviceTemperature = deviceTemperature;
    }

    public Integer getFull() {
        return full;
    }

    public void setFull(Integer full) {
        this.full = full;
    }

    public BigDecimal getDeviceHumidity() {
        return deviceHumidity;
    }

    public void setDeviceHumidity(BigDecimal deviceHumidity) {
        this.deviceHumidity = deviceHumidity;
    }

    @Override
    public String toString() {
        return "TcpDto{" +
                "deviceNum='" + deviceNum + '\'' +
                ", deviceTemperature=" + deviceTemperature +
                ", full=" + full +
                ", deviceHumidity=" + deviceHumidity +
                '}';
    }
}
