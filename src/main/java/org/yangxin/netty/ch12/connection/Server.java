package org.yangxin.netty.ch12.connection;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import static org.yangxin.netty.ch12.connection.Constant.BEGIN_PORT;
import static org.yangxin.netty.ch12.connection.Constant.N_PORT;

@SuppressWarnings("SameParameterValue")
public final class Server {

    public static void main(String[] args) {
        new Server().start(BEGIN_PORT, N_PORT);
    }

    private void start(Integer beginPort, Integer nPort) {
        System.out.println("server starting");

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);

        bootstrap.childHandler(new ConnectionCountHandler());

        for (int i = 0; i < nPort; i++) {
            int port = beginPort + i;
            bootstrap.bind(port)
                    .addListener((ChannelFutureListener) future -> System.out.println("bind success in port: " + port));
        }
        System.out.println("server started!");
    }
}