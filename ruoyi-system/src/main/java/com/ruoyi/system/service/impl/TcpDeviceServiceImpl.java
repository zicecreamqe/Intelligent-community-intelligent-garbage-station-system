package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.TcpDto;
import com.ruoyi.system.mapper.TcpDeviceMapper;
import com.ruoyi.system.service.TcpDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TcpDeviceServiceImpl implements TcpDeviceService {

    @Autowired
    private TcpDeviceMapper tcpDeviceMapper;

    @Override
    public void set(TcpDto tcpDto) {
        tcpDeviceMapper.set(tcpDto);
    }

    @Override
    public void setTemperature(TcpDto tcpDto) {
        tcpDeviceMapper.setTemperature(tcpDto);
    }

    @Override
    public void setFull(TcpDto tcpDto) {
        tcpDeviceMapper.setFull(tcpDto);
    }

    @Override
    public void setHumidity(TcpDto tcpDto) {
        tcpDeviceMapper.setHumidity(tcpDto);
    }
}
