package com.ruoyi.system.service;

import com.ruoyi.system.domain.TcpDto;

public interface TcpDeviceService {
    /**
     * 根据设备号来更新设备状态
     */
    void set(TcpDto tcpDto);

    /**
     * 设置设备温度
     * @param tcpDto 温度值和设备编号
     */
    void setTemperature(TcpDto tcpDto);

    /**
     * 设置设备满度
     * @param tcpDto 满度值和设备编号
     */
    void setFull(TcpDto tcpDto);

    /**
     * 设置设备湿度
     * @param tcpDto 湿度值和设备编号
     */
    void setHumidity(TcpDto tcpDto);
}
