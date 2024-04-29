package com.bxc.netty.upload;

import com.bxc.netty.upload.server.DiscardServer;

/**
 * @author: Bixc
 * @date: 2024/04/28 15:16
 **/
public class Starter {
    public static void main(String[] args) throws Exception {

        //请求上传
        //创建文件
        //将客户端数据写入本地磁盘
        //command 4  fileName 4 8

        //请求上传 -> 是否能够上传 -> 上传（客户端） -》将客户端数据写入本地磁盘
        new DiscardServer(9001).run();
    }
}
