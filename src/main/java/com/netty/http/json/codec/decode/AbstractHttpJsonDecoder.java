package com.netty.http.json.codec.decode;

import com.netty.http.json.bean.Order;
import com.netty.http.json.util.FastJsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;

/**
 * Created by jack on 2018/5/4.
 * 解码
 */
public abstract class AbstractHttpJsonDecoder<T> extends MessageToMessageDecoder<T> {
    private Class<?> clazz;
    private boolean isPrint;
    private final static Charset UTF_8 = Charset.forName("UTF-8");

    protected AbstractHttpJsonDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    protected AbstractHttpJsonDecoder(Class<?> clazz, boolean isPrint) {
        this.clazz = clazz;
        this.isPrint = isPrint;
    }

    /**
     * 字符串转换为java对象
     * @param context
     * @param body
     * @return
     */
    protected Object decode(ChannelHandlerContext context, ByteBuf body){
        String content = body.toString(UTF_8);
        if (isPrint)
            System.out.println("this body is:"+content);
        Object result = FastJsonUtil.parseObject(content, Order.class);
        return result;
    }

}
