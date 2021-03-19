package com.ctg.custpost.pojo.dto;

import com.ctg.custpost.pojo.until.BillEntity;

import java.util.Date;
import java.util.List;

public class PostItemAndUserDto {
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getTel_num() {
        return tel_num;
    }

    public void setTel_num(String tel_num) {
        this.tel_num = tel_num;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getPost_address() {
        return post_address;
    }

    public void setPost_address(String post_address) {
        this.post_address = post_address;
    }

    public String getFlight_num() {
        return flight_num;
    }

    public void setFlight_num(String flight_num) {
        this.flight_num = flight_num;
    }

    public Date getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(Date leave_time) {
        this.leave_time = leave_time;
    }

    public String getLeave_place() {
        return leave_place;
    }

    public void setLeave_place(String leave_place) {
        this.leave_place = leave_place;
    }

    public List<BillEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<BillEntity> orderList) {
        this.orderList = orderList;
    }

    private static final long serialVersionUID = 1L;
    /*
    *收件人（收件人必须是顾客本人）
    * */
    private String consignee;
    /*
     *收件人手机号
     * */
    private String tel_num;
    /*
     *收件人身份证号
     * */
    private String card_id;
    /*
     *邮寄地址
     * */
    private String post_address;
    /*
     *航班号
     * */
    private String flight_num;
    /*
     *离境时间
     * */
    private Date leave_time;
    /*
     *离境地点
     * */
    private String leave_place;
    /*
     *提货单记录
     * */
    private List<BillEntity> orderList;

}
