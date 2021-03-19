package com.ctg.custpost.pojo.dto;

import java.util.List;

public class UserAndOrderItemDto {
    public String getRet_card() {
        return ret_card;
    }

    public void setRet_card(String ret_card) {
        this.ret_card = ret_card;
    }

    public String getRet_name() {
        return ret_name;
    }

    public void setRet_name(String ret_name) {
        this.ret_name = ret_name;
    }

    public List<OrderItemDto> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderItemDto> orderList) {
        this.orderList = orderList;
    }

    private String ret_card;
    private String ret_name;
    private List<OrderItemDto> orderList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;
}
