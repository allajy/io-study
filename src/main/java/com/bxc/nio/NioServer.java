package com.bxc.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * nio演示代码
 * @author: Bixc
 * @date: 2024/04/28 09:36
 **/
public class NioServer {

    //保存客户端连接
    static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        //创建NIO ServerSocketChannel,与BIO的serverSocket类似
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new java.net.InetSocketAddress(9001));
        //设置ServerSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务启动成功");

        while (true) {
            //非阻塞模式accept方法不会阻塞，否则会阻塞
            //NIO的非阻塞是由操作系统内部实现的，底层调用了linux内核的accept函数
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel!=null){
                System.out.println("连接成功");
                //设置SocketChannel为非阻塞
                socketChannel.configureBlocking(false);
                //保存客户端连接在List中
                channelList.add(socketChannel);
            }
            //遍历连接进行数据读取 10w - 1000 读写事件
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()){
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                //非阻塞模式read方法不会阻塞，否则会阻塞
                int read = sc.read(byteBuffer);
                //如果有数据，把数据打印出来
                if (read>0){
                    System.out.println(Thread.currentThread().getName()+"接收到消息："+new String(byteBuffer.array()));
                } else if (read==-1) {   //如果客户端断开，把socket从集合中去掉
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }
            }
        }
    }

}
