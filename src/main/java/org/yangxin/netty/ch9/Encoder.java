package org.yangxin.netty.ch9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * |4|4|?|
 * -----------------
 * |length|age|name|
 *
 * @author yangxin
 * 2021/11/4 下午9:03
 */
public class Encoder extends MessageToByteEncoder<User> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, User user, ByteBuf byteBuf) {
        byte[] bytes = user.getName().getBytes();
        byteBuf.writeInt(4 + bytes.length);
        byteBuf.writeInt(user.getAge());
        byteBuf.writeBytes(bytes);
    }
}
