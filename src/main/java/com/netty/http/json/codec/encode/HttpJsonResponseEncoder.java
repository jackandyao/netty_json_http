package com.netty.http.json.codec.encode;

import com.netty.http.json.bean.http.HttpJsonResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

import java.util.List;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by jack on 2018/5/4.
 * 编码
 * 服务端编码
 * java对象转换为字符串
 */
public class HttpJsonResponseEncoder extends AbstractHttpJsonEncoder<HttpJsonResponse>  {
    @Override
    protected void encode(ChannelHandlerContext ctx, HttpJsonResponse msg, List<Object> out) throws Exception {
        //获取服务端的字节流
        ByteBuf body = encode(ctx, msg.getResult());
        //创建一个服务端响应对象
        FullHttpResponse response = msg.getHttpResponse();
        if (response == null) {
            //实例化一个
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, body);
        } else {
            response = new DefaultFullHttpResponse(msg.getHttpResponse()
                    .protocolVersion(), msg.getHttpResponse().status(),
                    body);
        }
        //设置内容格式为json
        response.headers().set(CONTENT_TYPE, "text/json");
        //设置内容的长度
        HttpUtil.setContentLength(response, body.readableBytes());
        //添加到结果中
        out.add(response);
    }
}
