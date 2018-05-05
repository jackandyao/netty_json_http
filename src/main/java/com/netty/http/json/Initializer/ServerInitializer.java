package com.netty.http.json.Initializer;

import com.netty.http.json.bean.Order;
import com.netty.http.json.codec.decode.HttpJsonRequestDecoder;
import com.netty.http.json.codec.encode.HttpJsonResponseEncoder;
import com.netty.http.json.handler.HttpJsonServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Created by jack on 2018/5/4.
 * 服务端初始化各种编解码器和具体的handler到channelpiple
 */
public class ServerInitializer  extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new HttpRequestDecoder());
        ch.pipeline().addLast(new HttpObjectAggregator(65536));
        ch.pipeline().addLast(new HttpJsonRequestDecoder(Order.class,true));

        ch.pipeline().addLast(new HttpResponseEncoder());
        ch.pipeline().addLast(new HttpJsonResponseEncoder());
        ch.pipeline().addLast(new HttpJsonServerHandler());
    }
}
