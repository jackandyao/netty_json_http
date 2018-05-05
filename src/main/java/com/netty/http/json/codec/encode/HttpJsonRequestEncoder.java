package com.netty.http.json.codec.encode;

import com.netty.http.json.bean.http.HttpJsonRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.net.InetAddress;
import java.util.List;

/**
 * Created by jack on 2018/5/4.
 * 编码
 * 客户端编码
 * java对象转换为字节流
 */
public class HttpJsonRequestEncoder extends AbstractHttpJsonEncoder<HttpJsonRequest> {
    @Override
    protected void encode(ChannelHandlerContext ctx, HttpJsonRequest msg, List<Object> out) throws Exception {
        ByteBuf body = encode(ctx,msg.getBody());
        //如果业务自定义http消息头,就直接使用,否则就创建一个
        FullHttpRequest request = msg.getRequest();
        if (request ==null){

            request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/do", body);
            //设置httpheaders  实际应该是通过读取配置文件来加载的
            HttpHeaders headers = request.headers();
            //设置host
            headers.set(HttpHeaderNames.HOST, InetAddress.getLocalHost().getHostAddress());
            //设置connection
            headers.set(HttpHeaderNames.CONNECTION, HttpHeaders.Values.CLOSE);
            //设置编码
            headers.set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP.toString() + ',' + HttpHeaderValues.DEFLATE.toString());
            //设置字符格式
            headers.set(HttpHeaderNames.ACCEPT_CHARSET, "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
            //设置语言
            headers.set(HttpHeaderNames.ACCEPT_LANGUAGE, "zh");
            //设置客户端
            headers.set(HttpHeaderNames.USER_AGENT, "Netty json Http Client side");
            //设置接收数据的格式
            headers.set(HttpHeaderNames.ACCEPT, "text/html,application/json;q=0.9,*/*;q=0.8");
        }
        HttpUtil.setContentLength(request,body.readableBytes());
        out.add(request);
    }
}
