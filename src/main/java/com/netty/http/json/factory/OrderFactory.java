package com.netty.http.json.factory;

import com.netty.http.json.bean.Order;

/**
 * Created by jack on 2018/5/4.
 */
public class OrderFactory {

    /**
     * 实例化一个order
     * @param value
     * @return
     */
    public static Order createOrder(long value){
        Order order = new Order();
        order.setOrderNumber(value);
        order.setBillTo("南京");
        order.setShipping("国内快递");
        order.setShipTo("上海");
        order.setTotal(120);
        order.setCustomer("贾红平");
        return order;
    }

    /**
     * 修改一个order
     * @param order
     */
    public static void setOrder(Order order){
        order.setBillTo("北京");
        order.setShipping("国内快递");
        order.setShipTo("天津");
        order.setTotal(120);
        order.setCustomer("姚杰");
    }
}
