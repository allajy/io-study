package com.bxc.netty.heartbeat.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author: Bixc
 * @date: 2024/04/28 15:22
 **/
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    int readTimeOut = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;

        if(event.state() == IdleState.READER_IDLE){
            readTimeOut++;
        }

        if(readTimeOut >= 3){
            System.out.println("超时超过3次，断开连接");
            ctx.close();
        }

        System.out.println("触发了：" + event.state() + "事件");
    }
}
