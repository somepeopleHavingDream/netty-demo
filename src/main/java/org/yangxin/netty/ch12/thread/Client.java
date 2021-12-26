package org.yangxin.netty.ch12.thread;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.util.concurrent.ExecutionException;

import static org.yangxin.netty.ch12.thread.Constant.PORT;

/**
 * @author yangxin
 * 2021/12/25 19:36
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "SameParameterValue"})
public class Client {

    private static final String SERVER_HOST = "127.0.0.1";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Client().start(PORT);
    }

    private void start(Integer port) throws ExecutionException, InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(Long.BYTES));
                socketChannel.pipeline().addLast(ClientBusinessHandler.INSTANCE);
            }
        });

        for (int i = 0; i < 1000; i++) {
            bootstrap.connect(SERVER_HOST, port).get();
        }
    }
}
