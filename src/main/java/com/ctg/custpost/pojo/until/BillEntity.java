package com.ctg.custpost.pojo.until;

public class BillEntity {
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getShxsdno() {
        return shxsdno;
    }

    public void setShxsdno(String shxsdno) {
        this.shxsdno = shxsdno;
    }

    public double getShoughtpay() {
        return shoughtpay;
    }

    public void setShoughtpay(double shoughtpay) {
        this.shoughtpay = shoughtpay;
    }

    private static final long serialVersionUID = 1L;

    private String market;

    private String shxsdno;

    private double shoughtpay;
}
