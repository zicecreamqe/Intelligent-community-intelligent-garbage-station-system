package com.ruoyi.system.tcp;

import com.ruoyi.system.controller.TcpDeviceController;
import com.ruoyi.system.domain.TcpDto;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Component
public class TCPServer implements Runnable {
    @Autowired
    private TcpDeviceController tcpDeviceController;

    @Override
    public void run() {
        //创建套接字： 指定服务器的端口号
        ServerSocket ss = null;
        Socket s = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            //创建Socket对象
            ss = new ServerSocket(8880);
            //监听（阻塞）
            s = ss.accept();
            while (true) {
                is = s.getInputStream();
                os = s.getOutputStream();
                //获取数据
                byte[] bys = new byte[1024];
                int len;
                len = is.read(bys);
                //输出数据
                InetAddress address = s.getInetAddress();
                String data = new String(bys, 0, len);
                //打印来请求着的ip地址
                System.out.println("sender:" + address);
                //打印请求过来的完整参数
                System.out.println(data);
                //数据截取
                //为了配合postman这里先注释掉
                String status = data.substring(0, 1);
                int intValue = Integer.parseInt(data.substring(1,4));
                int decimalValue = Integer.parseInt(data.substring(4,6));
                int value = intValue + decimalValue/100;
                String deviceNum = data.substring(6,18);

                //配合postman数据传输格式
                /*String status = data.substring(6, 7);
                int value = Integer.parseInt(data.substring(7, 10));
                String deviceNum = data.substring(10, 22);*/

                TcpDto tcpDto = new TcpDto();
                //设备编号无关设置的状态可以先设置进去
                tcpDto.setDeviceNum(deviceNum);
                if (status.equals("C")) {
                    tcpDto.setFull(value);
                    tcpDeviceController.setFull(tcpDto);
                } else if (status.equals("H")) {
                    tcpDto.setDeviceHumidity(BigDecimal.valueOf(value));
                    tcpDeviceController.setHumidity(tcpDto);
                } else if (status.equals("T")) {
                    tcpDto.setDeviceTemperature(BigDecimal.valueOf(value));
                    tcpDeviceController.setTemperature(tcpDto);
                }
                String response = "";
                os.write(response.getBytes(StandardCharsets.UTF_8), 0, response.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //@Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //创建套接字： 指定服务器的端口号
        ServerSocket ss = null;
        Socket s = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            //创建Socket对象
            ss = new ServerSocket(10086);
            while (true) {
                //监听（阻塞）
                s = ss.accept();
                is = s.getInputStream();
                os = s.getOutputStream();
                //获取数据
                byte[] bys = new byte[1024];
                int len;
                len = is.read(bys);
                //输出数据
                InetAddress address = s.getInetAddress();
                String data = new String(bys, 0, len);
                //打印来请求着的ip地址
                System.out.println("sender:" + address);
                //打印请求过来的完整参数
                System.out.println(data);
                //数据截取
                /*//为了配合postman这里先注释掉
                String status = data.substring(0, 1);
                int value = Integer.parseInt(data.substring(1,4));
                String deviceNum = data.substring(4);*/

                //配合postman数据传输格式
                String status = data.substring(6, 7);
                int value = Integer.parseInt(data.substring(7, 10));
                String deviceNum = data.substring(10, 22);

                TcpDto tcpDto = new TcpDto();
                //设备编号无关设置的状态可以先设置进去
                tcpDto.setDeviceNum(deviceNum);
                if (status.equals("C")) {
                    tcpDto.setFull(value);
                    tcpDeviceController.setFull(tcpDto);
                } else if (status.equals("H")) {
                    tcpDto.setDeviceHumidity(BigDecimal.valueOf(value));
                    tcpDeviceController.setHumidity(tcpDto);
                } else if (status.equals("T")) {
                    tcpDto.setDeviceTemperature(BigDecimal.valueOf(value));
                    tcpDeviceController.setTemperature(tcpDto);
                }
                String response = "OK";
                os.write(response.getBytes(StandardCharsets.UTF_8), 0, response.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //@Bean
    public void connect() {
        //创建套接字： 指定服务器的端口号
        ServerSocket ss = null;
        Socket s = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            //创建Socket对象
            ss = new ServerSocket(10086);
            while (true) {
                //监听（阻塞）
                s = ss.accept();
                is = s.getInputStream();
                os = s.getOutputStream();
                //获取数据
                byte[] bys = new byte[1024];
                int len;
                len = is.read(bys);
                //输出数据
                InetAddress address = s.getInetAddress();
                String data = new String(bys, 0, len);
                //打印来请求着的ip地址
                System.out.println("sender:" + address);
                //打印请求过来的完整参数
                System.out.println(data);
                //数据截取
                /*//为了配合postman这里先注释掉
                String status = data.substring(0, 1);
                int value = Integer.parseInt(data.substring(1,4));
                String deviceNum = data.substring(4);*/

                //配合postman数据传输格式
                String status = data.substring(6, 7);
                int value = Integer.parseInt(data.substring(7, 10));
                String deviceNum = data.substring(10, 22);

                TcpDto tcpDto = new TcpDto();
                //设备编号无关设置的状态可以先设置进去
                tcpDto.setDeviceNum(deviceNum);
                if (status.equals("C")) {
                    tcpDto.setFull(value);
                    tcpDeviceController.setFull(tcpDto);
                } else if (status.equals("H")) {
                    tcpDto.setDeviceHumidity(BigDecimal.valueOf(value));
                    tcpDeviceController.setHumidity(tcpDto);
                } else if (status.equals("T")) {
                    tcpDto.setDeviceTemperature(BigDecimal.valueOf(value));
                    tcpDeviceController.setTemperature(tcpDto);
                }
                String response = "OK";
                os.write(response.getBytes(StandardCharsets.UTF_8), 0, response.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) throws IOException {
        TcpDeviceController tcpDeviceController = new TcpDeviceController();
        //创建套接字： 指定服务器的端口号
        ServerSocket ss = null;
        Socket s = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            //创建Socket对象
            ss = new ServerSocket(10086);
            while (true) {
                //监听（阻塞）
                s = ss.accept();
                is = s.getInputStream();
                os = s.getOutputStream();
                //获取数据
                byte[] bys = new byte[1024];
                int len;
                len = is.read(bys);
                //输出数据
                InetAddress address = s.getInetAddress();
                String data = new String(bys, 0, len);
                //打印来请求着的ip地址
                System.out.println("sender:" + address);
                //打印请求过来的完整参数
                System.out.println(data);
                //数据截取
                /*//为了配合postman这里先注释掉
                String status = data.substring(0, 1);
                int value = Integer.parseInt(data.substring(1,4));
                String deviceNum = data.substring(4);*/

                //配合postman数据传输格式
                String status = data.substring(6, 7);
                int value = Integer.parseInt(data.substring(7, 10));
                String deviceNum = data.substring(10, 22);

                TcpDto tcpDto = new TcpDto();
                //设备编号无关设置的状态可以先设置进去
                tcpDto.setDeviceNum(deviceNum);
                if (status.equals("C")) {
                    tcpDto.setFull(value);
                    tcpDeviceController.setFull(tcpDto);
                } else if (status.equals("H")) {
                    tcpDto.setDeviceHumidity(BigDecimal.valueOf(value));
                    tcpDeviceController.setHumidity(tcpDto);
                } else if (status.equals("T")) {
                    tcpDto.setDeviceTemperature(BigDecimal.valueOf(value));
                    tcpDeviceController.setTemperature(tcpDto);
                }
                String response = "OK";
                os.write(response.getBytes(StandardCharsets.UTF_8), 0, response.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
