package com.netty.http.json.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by jack on 2018/5/4.
 * 封装json工具类
 */
public class FastJsonUtil {

    /**
     * jsonstr to javabean
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonString,Class clazz){
        return  (T)JSONObject.parseObject(jsonString, clazz);
    }

    /**
     * javabean to jsonstr
     * @param t
     * @param <T>
     * @return
     */
    public static <T>  String parseJsonStr(T t){
        return JSONObject.toJSONString(t);
    }
}
