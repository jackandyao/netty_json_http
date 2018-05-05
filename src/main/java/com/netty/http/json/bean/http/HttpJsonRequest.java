package com.netty.http.json.bean.http;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * Created by jack on 2018/5/4.
 * 封装请求参数
 */
public class HttpJsonRequest {
    private FullHttpRequest request;
    private Object body;

    public Object getBody() {
        return this.body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public FullHttpRequest getRequest() {
        return this.request;
    }

    public void setRequest(FullHttpRequest request) {
        this.request = request;
    }

    public HttpJsonRequest(FullHttpRequest request, Object body) {
        this.request = request;
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpJsonRequest [request=" + request + ", body =" + body + "]";
    }
}
