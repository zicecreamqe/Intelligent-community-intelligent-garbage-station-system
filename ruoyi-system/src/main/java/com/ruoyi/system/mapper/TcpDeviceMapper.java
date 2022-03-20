package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.TcpDto;
import org.springframework.stereotype.Repository;

@Repository
public interface TcpDeviceMapper {
    void set(TcpDto tcpDto);

    void setTemperature(TcpDto tcpDto);

    void setFull(TcpDto tcpDto);

    void setHumidity(TcpDto tcpDto);
}
