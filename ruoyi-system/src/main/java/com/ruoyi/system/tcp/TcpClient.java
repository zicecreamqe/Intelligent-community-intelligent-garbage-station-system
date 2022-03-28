package com.ruoyi.system.tcp;

import com.ruoyi.system.controller.IotDeviceController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TcpClient {
    @Autowired
    private IotDeviceController iotDeviceController;

    public static void main(String[] args) {
        //1.创建套接字：指定服务器的ip和端口号：
        Socket s = null;
        OutputStream os = null;
        InputStream is = null;
        DataInputStream dis = null;
        try {
            s = new Socket("192.168.31.30", 10186);
            os = s.getOutputStream();
            String out = "shuchu";
            os.write(out.getBytes(StandardCharsets.UTF_8),0,out.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//注意关闭流的操作必须单独trycatch，因为要是都放一起的话一个抛异常了可能下面的释放流就执行不到了就没有关闭到了
            //3.关闭流  +  关闭网络资源：
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
