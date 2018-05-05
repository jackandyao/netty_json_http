package com.netty.http.json.bean.http;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * Created by jack on 2018/5/4.
 * 封装响应结果
 */
public class HttpJsonResponse {
    private FullHttpResponse httpResponse;
    private Object result;

    public HttpJsonResponse(FullHttpResponse httpResponse, Object result) {
        this.httpResponse = httpResponse;
        this.result = result;
    }

    public FullHttpResponse getHttpResponse() {
        return this.httpResponse;
    }

    public void setHttpResponse(FullHttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    @Override
    public String toString() {
        return "HttpJsonResponse [httpResponse=" + httpResponse + ", result="
                + result + "]";
    }
}
