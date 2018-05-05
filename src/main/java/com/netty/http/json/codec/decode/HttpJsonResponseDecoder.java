package com.netty.http.json.codec.decode;

import com.netty.http.json.bean.http.HttpJsonResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

import java.util.List;

/**
 * Created by jack on 2018/5/4.
 * 解码
 * 客户端解码
 * 字节流转换javabean
 */
public class HttpJsonResponseDecoder extends AbstractHttpJsonDecoder<FullHttpResponse> {
    public HttpJsonResponseDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    /**
     * 构造器
     *
     * @param clazz   解码的对象信息
     * @param isPrint 是否需要打印
     */
    public HttpJsonResponseDecoder(Class<?> clazz, boolean isPrint) {
        super(clazz, isPrint);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpResponse msg, List<Object> out) throws Exception {
        System.out.println("begin decode value....");
        HttpJsonResponse response = new HttpJsonResponse(msg,decode(ctx,msg.content()));
        out.add(response);
    }
}
