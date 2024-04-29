package com.bxc.netty.base.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author: Bixc
 * @date: 2024/04/28 15:22
 **/
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        System.out.println("有客户端连接了");
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf out = (ByteBuf) o;
        String data = out.toString(CharsetUtil.UTF_8);
        System.out.println(data);
        short length = (short) data
                .getBytes().length;
        ByteBuf byteBuf = channelHandlerContext.alloc().directBuffer(8);
        byteBuf.writeShort(length);
        byteBuf.writeBytes(data.getBytes());
        channelHandlerContext.writeAndFlush(byteBuf);
    }
}
