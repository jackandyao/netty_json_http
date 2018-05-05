package com.netty.http.json.handler;

import com.netty.http.json.bean.Order;
import com.netty.http.json.bean.http.HttpJsonRequest;
import com.netty.http.json.bean.http.HttpJsonResponse;
import com.netty.http.json.factory.OrderFactory;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by jack on 2018/5/4.
 * 服务端处理业务的handler
 */
public class HttpJsonServerHandler extends SimpleChannelInboundHandler<HttpJsonRequest> {

    /**
     * 服务端读取客户端的数据并修改获取到的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, HttpJsonRequest msg) throws Exception {
       //读取客户端的数据
        HttpRequest request = msg.getRequest();
        Order order = (Order)msg.getBody();
        System.out.println("Http server receive request : " + order);
        //修改客户端的数据
        OrderFactory.setOrder(order);
        //返回给客户端
        ChannelFuture future = ctx.writeAndFlush(new HttpJsonResponse(null,order));
        //后面需要仔细研读
        if (!HttpUtil.isKeepAlive(request)) {
            future.addListener(new GenericFutureListener<Future<? super Void>>() {
                public void operationComplete(Future future) throws Exception {
                    ctx.close();
                }
            });
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }

    private static void sendError(ChannelHandlerContext ctx,
                                  HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                status, Unpooled.copiedBuffer("失败: " + status.toString()
                + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
