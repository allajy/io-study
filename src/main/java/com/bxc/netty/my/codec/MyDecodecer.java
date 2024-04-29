package com.bxc.netty.my.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author: Bixc
 * @date: 2024/04/28 16:43
 **/
public class MyDecodecer extends ByteToMessageDecoder{

    //数据长度+数据
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes()<4){
            return;
        }

        //数据长度 4 + 10000 9999
        int i = byteBuf.readInt();
        if(byteBuf.readableBytes()<i){
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data = new byte[i];//10000
        byteBuf.readBytes(data);
        System.out.println(new String(data));
        byteBuf.markReaderIndex();//100004

    }
}
