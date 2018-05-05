package com.netty.http.json.client;

import com.netty.http.json.Initializer.ClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by jack on 2018/5/4.
 * 客户端工作
 */
public class HttpJsonClient {
    static EventLoopGroup group;
    static Bootstrap client;

    static {
        group = new NioEventLoopGroup();
        client = new Bootstrap();
        client.group(group).channel(NioSocketChannel.class);
        client.option(ChannelOption.SO_SNDBUF,128);
        client.option(ChannelOption.TCP_NODELAY,false);
        client.handler(new ClientInitializer());
    }

    /**
     * 启动客户端
     * @param port
     */
    public static  void connect(int port){
        try {
            ChannelFuture future = client.connect(new InetSocketAddress(port)).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        connect(8080);
    }
}
