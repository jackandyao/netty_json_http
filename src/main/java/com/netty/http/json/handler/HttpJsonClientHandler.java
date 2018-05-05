package com.netty.http.json.handler;

import com.netty.http.json.bean.http.HttpJsonRequest;
import com.netty.http.json.factory.OrderFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by jack on 2018/5/4.
 * 客户端处理业务的handler
 */
public class HttpJsonClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端连接到服务端的时候 开始发送数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接上服务器:"+ctx.channel().remoteAddress());
        HttpJsonRequest request = new HttpJsonRequest(null, OrderFactory.createOrder(1001));
        ctx.writeAndFlush(request);
    }

    /**
     * 接收到服务端返回回来的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.getClass().getSimpleName());
        System.out.println("客户端接收到服务端返回的数据:"+msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
