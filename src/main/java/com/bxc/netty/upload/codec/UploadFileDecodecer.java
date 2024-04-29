package com.bxc.netty.upload.codec;

import com.bxc.netty.upload.FileDto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author: Bixc
 * @date: 2024/04/28 17:58
 **/
public class UploadFileDecodecer extends ByteToMessageDecoder {

    //请求上传
    //创建文件
    //将客户端数据写入本地磁盘
    //command 4  fileName 4 8
    //数据长度 + 数据
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if(byteBuf.readableBytes()<4){
            return;
        }
        // command 4
        int command = byteBuf.readInt();

        FileDto fileDto = new FileDto();
        int fileNameLen = byteBuf.readInt();
        if(byteBuf.readableBytes() < fileNameLen){
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data = new byte[fileNameLen];
        byteBuf.readBytes(data);
        String fileName = new String(data);
        fileDto.setCommand(command);
        fileDto.setFileName(fileName);

        if(command==2){
            int dataLen = byteBuf.readInt();
            if(byteBuf.readableBytes() < dataLen){
                byteBuf.resetReaderIndex();
                return;
            }
            byte[] fileData = new byte[dataLen];
            byteBuf.readBytes(fileData);
            fileDto.setBytes(fileData);
        }
        byteBuf.markReaderIndex();
        list.add(fileDto);
    }
}
