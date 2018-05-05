package com.netty.http.json.codec.encode;

import com.netty.http.json.util.FastJsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.Charset;

/**
 * Created by jack on 2018/5/4.
 * 编码
 */
public abstract class AbstractHttpJsonEncoder<T> extends MessageToMessageEncoder<T> {
    public final static Charset charset = Charset.forName("utf-8");
    /**
     * 对象转换为bytebuf
     * @param context
     * @param body
     */
    protected ByteBuf encode (ChannelHandlerContext context,Object body){
        String str = FastJsonUtil.parseJsonStr(body);
        ByteBuf byteBuf = Unpooled.copiedBuffer(str,charset);
        return byteBuf;
    }
}
