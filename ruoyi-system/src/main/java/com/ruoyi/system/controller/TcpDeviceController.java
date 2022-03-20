package com.ruoyi.system.controller;

import com.ruoyi.system.domain.TcpDto;
import com.ruoyi.system.service.TcpDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TcpDeviceController {
    @Autowired
    private TcpDeviceService tcpDeviceService;

    public void set(TcpDto tcpDto) {
        tcpDeviceService.set(tcpDto);
    }

    public void setTemperature(TcpDto tcpDto) {
        tcpDeviceService.setTemperature(tcpDto);
    }

    public void setFull(TcpDto tcpDto) {
        tcpDeviceService.setFull(tcpDto);
    }

    public void setHumidity(TcpDto tcpDto) {
        tcpDeviceService.setHumidity(tcpDto);
    }
}
