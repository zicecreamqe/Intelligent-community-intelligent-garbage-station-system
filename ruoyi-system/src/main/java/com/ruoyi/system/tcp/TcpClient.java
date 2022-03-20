package com.ruoyi.system.tcp;

import com.ruoyi.system.controller.IotDeviceController;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    @Autowired
    private IotDeviceController iotDeviceController;

    public static void main(String[] args) {
        //1.创建套接字：指定服务器的ip和端口号：
        Socket s = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        InputStream is = null;
        DataInputStream dis = null;
        try {
            s = new Socket("192.168.199.217", 8888);
            //录入用户的账号和密码：
            Scanner sc = new Scanner(System.in);
            System.out.println("请录入您的账号：");
            String name = sc.next();
            System.out.println("请录入您的密码：");
            String pwd = sc.next();
            //将账号和密码封装为一个User的对象：
            /*User user = new User(name, pwd);
            //2.对于程序员来说，向外发送数据 感受 --》利用输出流：
            os = s.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(user);*/
            //接收服务器端的回话--》利用输入流：
            is = s.getInputStream();
            dis = new DataInputStream(is);
            boolean b = dis.readBoolean();
            if (b) {
                System.out.println("恭喜，登录成功");
            } else {
                System.out.println("对不起，登录失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//注意关闭流的操作必须单独trycatch，因为要是都放一起的话一个抛异常了可能下面的释放流就执行不到了就没有关闭到了
            //3.关闭流  +  关闭网络资源：
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
