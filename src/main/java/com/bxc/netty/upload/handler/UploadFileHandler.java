package com.bxc.netty.upload.handler;

import com.bxc.netty.upload.FileDto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.io.*;

/**
 * @author: Bixc
 * @date: 2024/04/28 15:22
 **/
public class UploadFileHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if(o instanceof FileDto){
            FileDto fileDto = (FileDto) o;
            if(fileDto.getCommand() == 1){
                //创建文件
                File file = new File("C://"+fileDto.getFileName());
                if(!file.exists()){
                    file.createNewFile();
                }
            }else if(fileDto.getCommand() == 2){
                //写入文件
                save2File("C://"+fileDto.getFileName(),fileDto.getBytes());
            }
        }
    }

    public static boolean save2File(String fname, byte[] msg){
        OutputStream fos = null;
        try{
            File file = new File(fname);
            File parent = file.getParentFile();
            boolean bool;
            if ((!parent.exists()) &
                    (!parent.mkdirs())) {
                return false;
            }
            fos = new FileOutputStream(file,true);
            fos.write(msg);
            fos.flush();
            return true;
        }catch (FileNotFoundException e){
            return false;
        }catch (IOException e){
            File parent;
            return false;
        }
        finally{
            if (fos != null) {
                try{
                    fos.close();
                }catch (IOException e) {}
            }
        }
    }
}
