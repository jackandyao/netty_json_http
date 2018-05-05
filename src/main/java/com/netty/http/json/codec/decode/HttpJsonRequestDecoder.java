package com.netty.http.json.codec.decode;

import com.netty.http.json.bean.http.HttpJsonRequest;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import java.util.List;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by jack on 2018/5/4.
 * 服务端的解码
 * 字符串转换为对象
 */
public class HttpJsonRequestDecoder extends AbstractHttpJsonDecoder<FullHttpRequest> {

    /**
     * 解码
     * @param ctx
     * @param msg
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpRequest msg, List<Object> out) throws Exception {
        if (!msg.decoderResult().isSuccess()){
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        HttpJsonRequest request = new HttpJsonRequest(msg,decode(ctx,msg.content()));
        out.add(request);
    }

    public HttpJsonRequestDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    /**
     * 构造器
     *
     * @param clazz   解码的对象信息
     * @param isPrint 是否需要打印
     */
    public HttpJsonRequestDecoder(Class<?> clazz, boolean isPrint) {
        super(clazz, isPrint);
    }

    /**
     * 测试的话，直接封装，实战中需要更健壮的处理
     */
    private static void sendError(ChannelHandlerContext ctx,
                                  HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                status, Unpooled.copiedBuffer("Failure: " + status.toString()
                + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
