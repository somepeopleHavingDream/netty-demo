package org.yangxin.netty.ch6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author yangxin
 * 2021/10/27 下午9:19
 */
public class OutboundHandlerC extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        System.out.println("OutboundHandlerC: " + msg);
        ctx.write(msg, promise);
//        throw new BusinessException("from OutboundHandlerC");
    }
}
