package com.bxc.netty.chat;

import com.bxc.netty.chat.server.DiscardServer;

/**
 * @author: Bixc
 * @date: 2024/04/28 15:16
 **/
public class Starter {
    public static void main(String[] args) throws Exception {
        new DiscardServer(9001).run();
    }
}
