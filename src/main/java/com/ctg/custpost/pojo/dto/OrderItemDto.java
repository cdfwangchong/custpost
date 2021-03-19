package com.ctg.custpost.pojo.dto;

import java.sql.Timestamp;

public class OrderItemDto {
    public String getRet_picknum() {
        return ret_picknum;
    }

    public void setRet_picknum(String ret_picknum) {
        this.ret_picknum = ret_picknum;
    }

    public String getRet_mark() {
        return ret_mark;
    }

    public void setRet_mark(String ret_mark) {
        this.ret_mark = ret_mark;
    }

    public Double getRet_money() {
        return ret_money;
    }

    public void setRet_money(Double ret_money) {
        this.ret_money = ret_money;
    }

    public String getFlight_num() {
        return flight_num;
    }

    public void setFlight_num(String flight_num) {
        this.flight_num = flight_num;
    }

    public Timestamp getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(Timestamp leave_time) {
        this.leave_time = leave_time;
    }

    public String getLeave_place() {
        return leave_place;
    }

    public void setLeave_place(String leave_place) {
        this.leave_place = leave_place;
    }

    private String ret_picknum;
    private String ret_mark;
    private Double ret_money;
    private String flight_num;
    private Timestamp leave_time;
    private String leave_place;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 1L;

}
