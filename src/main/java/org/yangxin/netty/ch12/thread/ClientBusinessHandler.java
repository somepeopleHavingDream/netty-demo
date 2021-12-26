package org.yangxin.netty.ch12.thread;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yangxin
 * 2021/12/25 19:31
 */
@SuppressWarnings({"BusyWait", "AlibabaAvoidManuallyCreateThread"})
@ChannelHandler.Sharable
public class ClientBusinessHandler extends SimpleChannelInboundHandler<ByteBuf> {

    public static final ChannelHandler INSTANCE = new ClientBusinessHandler();

    private static final AtomicLong BEGIN_TIME = new AtomicLong(0);
    private static final AtomicLong TOTAL_RESPONSE_TIME = new AtomicLong(0);
    private static final AtomicInteger TOTAL_REQUEST = new AtomicInteger(0);

    public static final Thread THREAD = new Thread(() -> {
        try {
            while (true) {
                long duration = System.currentTimeMillis() - BEGIN_TIME.get();
                if (duration != 0) {
                    System.out.println("qps: " + 1000L * TOTAL_REQUEST.get() / duration
                            + ", " + "avg response time: "
                    + ((float) TOTAL_RESPONSE_TIME.get()) / TOTAL_REQUEST.get());
                    Thread.sleep(2000);
                }
            }
        } catch (InterruptedException ignored) {
        }
    });

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.executor().scheduleAtFixedRate(() -> {
            ByteBuf byteBuf = ctx.alloc().ioBuffer();
            byteBuf.writeLong(System.currentTimeMillis());
            ctx.channel().writeAndFlush(byteBuf);
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) {
        TOTAL_RESPONSE_TIME.addAndGet(System.currentTimeMillis() - msg.readLong());
        TOTAL_REQUEST.incrementAndGet();

        if (BEGIN_TIME.compareAndSet(0, System.currentTimeMillis())) {
            THREAD.start();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    }
}
