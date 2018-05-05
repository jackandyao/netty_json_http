package com.netty.http.json.bean;

/**
 * 订单信息
 */
public class Order {

    private long orderNumber;//订单号

    private String customer;//顾客信息


    private String billTo;//发送地址

    private String shipping;//快递类型


    private String shipTo;//收货地址

    private int total;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
	return "Order [orderNumber=" + orderNumber + ", customer=" + customer
		+ ", billTo=" + billTo + ", shipping=" + shipping.toString()
		+ ", shipTo=" + shipTo + ", total=" + total + "]";
    }



}