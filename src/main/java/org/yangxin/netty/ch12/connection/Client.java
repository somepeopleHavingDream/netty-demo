package org.yangxin.netty.ch12.connection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ExecutionException;

import static org.yangxin.netty.ch12.connection.Constant.BEGIN_PORT;
import static org.yangxin.netty.ch12.connection.Constant.N_PORT;

@SuppressWarnings("SameParameterValue")
public class Client {

    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) {
        new Client().start(BEGIN_PORT, N_PORT);
    }

    private void start(Integer beginPort, Integer nPort) {
        System.out.println("client starting......");

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) {
            }
        });

        int index = 0;
        int port;
        while (!Thread.interrupted()) {
            port = beginPort + index;
            try {
                ChannelFuture channelFuture = bootstrap.connect(SERVER_HOST, port);
                channelFuture.addListener((ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        System.out.println("connect failed, exit!");
                        System.exit(0);
                    }
                });

                channelFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            if (++index == nPort) {
                index = 0;
            }
        }
    }
}
