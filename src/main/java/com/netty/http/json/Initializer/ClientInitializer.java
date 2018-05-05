package com.netty.http.json.Initializer;

import com.netty.http.json.bean.Order;
import com.netty.http.json.codec.decode.HttpJsonRequestDecoder;
import com.netty.http.json.codec.decode.HttpJsonResponseDecoder;
import com.netty.http.json.codec.encode.HttpJsonRequestEncoder;
import com.netty.http.json.handler.HttpJsonClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;


/**
 * Created by jack on 2018/5/4.
 * 客户端初始化各种编解码器和具体的handler到channelpiple
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new HttpResponseDecoder());
        ch.pipeline().addLast(new HttpObjectAggregator(65536));
        ch.pipeline().addLast(new HttpJsonResponseDecoder(Order.class,true));

        ch.pipeline().addLast(new HttpRequestEncoder());
        ch.pipeline().addLast(new HttpJsonRequestEncoder());
        ch.pipeline().addLast(new HttpJsonClientHandler());

    }
}
