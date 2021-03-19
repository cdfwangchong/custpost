package com.ctg.custpost.pojo.dto;

import com.ctg.custpost.pojo.until.BillEntity;

import java.util.List;

public class XsdnoDto {
    public List<BillEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<BillEntity> orderList) {
        this.orderList = orderList;
    }

    public String getRet_name() {
        return ret_name;
    }

    public void setRet_name(String ret_name) {
        this.ret_name = ret_name;
    }

    public String getRet_card() {
        return ret_card;
    }

    public void setRet_card(String ret_card) {
        this.ret_card = ret_card;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private List<BillEntity> orderList;

    private String ret_name;

    private String ret_card;

    private static final long serialVersionUID = 1L;
}
