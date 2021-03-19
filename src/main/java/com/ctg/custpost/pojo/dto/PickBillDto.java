package com.ctg.custpost.pojo.dto;

public class PickBillDto {
    public String getGbid() {
        return gbid;
    }

    public void setGbid(String gbid) {
        this.gbid = gbid;
    }

    public String getGb_name() {
        return gb_name;
    }

    public void setGb_name(String gb_name) {
        this.gb_name = gb_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTrans_price() {
        return trans_price;
    }

    public void setTrans_price(double trans_price) {
        this.trans_price = trans_price;
    }

    public double getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(double trans_amount) {
        this.trans_amount = trans_amount;
    }

    private String gbid;
    private String gb_name;
    private int count;
    private double trans_price;
    private double trans_amount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;
}
