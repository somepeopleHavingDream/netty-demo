package org.yangxin.netty.ch6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2021/10/27 下午9:19
 */
public class OutboundHandlerB extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        System.out.println("OutboundHandlerB: " + msg);
        ctx.write(msg, promise);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            ctx.channel().write("hello world!");
//            ctx.write("hello world");
        }, 3, TimeUnit.SECONDS);
    }
}
