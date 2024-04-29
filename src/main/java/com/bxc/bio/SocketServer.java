package com.bxc.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio演示代码
 * @author: Bixc
 * @date: 2024/04/28 09:13
 **/
public class SocketServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            System.out.println("等待连接..");
            //阻塞方法
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接了..");

//            handler(clientSocket);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(clientSocket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

    private static void handler(Socket clientSocket) throws Exception{
        byte[] bytes = new byte[1024];
        System.out.println("准备read..");
        //接收客户端的数据，阻塞方法，没有数据可读时就阻塞
        int read = clientSocket.getInputStream().read(bytes);
        System.out.println("read完毕。。");
        if (read != -1) {
            System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
        }
    }


}
