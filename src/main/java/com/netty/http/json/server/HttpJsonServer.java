package com.netty.http.json.server;
import com.netty.http.json.Initializer.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


import java.net.InetSocketAddress;
/**
 * Created by jack on 2018/5/4.
 * 创建服务端
 */
@SuppressWarnings("all")
public class HttpJsonServer {

    static EventLoopGroup bossLoopGroup;//主线程
    static EventLoopGroup workLoopGroup;//从线程
    static ServerBootstrap serverBootstrap;//服务

    static {
        bossLoopGroup = new NioEventLoopGroup();
        workLoopGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossLoopGroup,workLoopGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.option(ChannelOption.SO_BACKLOG,128);
        serverBootstrap.option(ChannelOption.SO_SNDBUF,128);
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE,true);
        serverBootstrap.childHandler(new ServerInitializer());

    }

    /**
     * 启动服务器
     * @param port
     */
    public static void run(int port){
        try {
            ChannelFuture future = serverBootstrap.bind(new InetSocketAddress(port)).sync();
            System.out.println("HTTP订购服务器启动，网址是 : " + "http://localhost:" + port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossLoopGroup.shutdownGracefully();
            workLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        run(8080);
    }
}
