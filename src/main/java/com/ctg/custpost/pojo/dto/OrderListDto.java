package com.ctg.custpost.pojo.dto;

import java.util.List;

public class OrderListDto {
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<PickBillDto> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<PickBillDto> orderList) {
        this.orderList = orderList;
    }

    private static final long serialVersionUID = 1L;
    private List<PickBillDto> orderList;
}
