package org.yangxin.netty.ch12.connection;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@ChannelHandler.Sharable
public class ConnectionCountHandler extends ChannelInboundHandlerAdapter {

    private final AtomicInteger nConnection = new AtomicInteger();

    public ConnectionCountHandler() {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> System.out.println("connection: " + nConnection.get()),
                        0, 2, TimeUnit.SECONDS);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        nConnection.incrementAndGet();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        nConnection.decrementAndGet();
    }
}
