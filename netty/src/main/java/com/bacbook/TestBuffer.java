package com.bacbook;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestBuffer {
    public static void main(String[] args) {

        // FileChannel
        // 1.输入输出流 2.RandomAccessFile
        try (FileChannel channel = new FileInputStream("netty/src/main/resources/data.txt").getChannel()) {
            //准备缓冲区,10个字节
            ByteBuffer buffer  = ByteBuffer.allocate(10);
            while (true){
                //从channel读取数据,向buffer写入
                int len = channel.read(buffer);
                log.debug("取到的字节 {}",len);
                if (len == -1){
                    break;
                }
                //打印buffer的内容
                buffer.flip(); //切换buffer的读模式
                while (buffer.hasRemaining()){ //是否有剩余的未读数据
                    byte b = buffer.get();
                    log.debug("实际字节: {} ",(char)b);
                }
                //切换为写模式
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
