package org.yangxin.netty.ch9;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yangxin
 * 2021/11/4 下午8:58
 */
public class BizHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        User user = new User(19, "zhangsan");
        ctx.channel().writeAndFlush(user);
    }
}
