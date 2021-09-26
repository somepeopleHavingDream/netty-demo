package org.yangxin.netty.ch3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2021/9/26 17:43
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("channelRegistered");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        System.out.println("handlerAdded");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        new Thread(() -> {
            // 耗时的操作
            String result = loadFromDb();

            ctx.channel().writeAndFlush(result);
            ctx.executor().schedule(() -> {}, 1, TimeUnit.SECONDS);
        }).start();
    }

    private String loadFromDb() {
        return "hello world!";
    }
}
