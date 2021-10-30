package org.yangxin.netty.ch6.exceptionspread;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.yangxin.netty.ch6.BusinessException;

/**
 * @author yangxin
 * 2021/10/30 下午7:43
 */
public class InboundHandlerB extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        throw new BusinessException("from InboundHandlerB");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("org.yangxin.netty.ch6.exceptionspread.InboundHandlerB.exceptionCaught");

        ctx.fireExceptionCaught(cause);
    }
}
