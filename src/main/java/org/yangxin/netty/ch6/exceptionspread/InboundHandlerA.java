package org.yangxin.netty.ch6.exceptionspread;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yangxin
 * 2021/10/30 下午7:43
 */
public class InboundHandlerA extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("org.yangxin.netty.ch6.exceptionspread.InboundHandlerA.exceptionCaught");

        ctx.fireExceptionCaught(cause);
    }
}
