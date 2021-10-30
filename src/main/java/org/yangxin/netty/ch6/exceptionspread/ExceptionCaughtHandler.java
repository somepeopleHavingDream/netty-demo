package org.yangxin.netty.ch6.exceptionspread;

import com.sun.javafx.runtime.async.BackgroundExecutor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.yangxin.netty.ch6.BusinessException;

/**
 * @author yangxin
 * 2021/10/30 下午8:11
 */
public class ExceptionCaughtHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof BusinessException) {
            System.out.println("BusinessException");
        }
    }
}
