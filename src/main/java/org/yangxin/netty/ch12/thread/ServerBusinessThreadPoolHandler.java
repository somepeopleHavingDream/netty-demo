package org.yangxin.netty.ch12.thread;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangxin
 * 2021/12/26 14:08
 */
@SuppressWarnings("AlibabaThreadPoolCreation")
@ChannelHandler.Sharable
public class ServerBusinessThreadPoolHandler extends ServerBusinessHandler{

    public static final ChannelHandler INSTANCE = new ServerBusinessThreadPoolHandler();
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(50);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        ByteBuf byteBuf = Unpooled.directBuffer();
        byteBuf.writeBytes(msg);
        THREAD_POOL.submit(() -> {
            Object result = getResult(byteBuf);
            ctx.channel().writeAndFlush(result);
        });
    }
}
