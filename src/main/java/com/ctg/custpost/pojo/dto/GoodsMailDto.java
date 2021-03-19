package com.ctg.custpost.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * GOODSMAIL
 * @author 
 */
@Data
public class GoodsMailDto implements Serializable {
    /**
     * 邮寄订单号
     */
    private String postId;

    /**
     * 快递单号
     */
    private String expressId;

    /**
     * 收件人
     */
    private String consignee;

    /**
     * 电话号码
     */
    private String telNum;

    /**
     * 证件号
     */
    private String cardId;

    /**
     * 航班号
     */
    private String flightNum;

    /**
     * 离境地点
     */
    private String leavePlace;

    /**
     * 邮寄地址
     */
    private String postAddress;

    /**
     * 离境时间
     */
    private Date flightTime;

    /**
     * 备用一
     */
    private String str1;

    /**
     * 备用二
     */
    private String str2;

    /**
     * 备用三
     */
    private String str3;

    private static final long serialVersionUID = 1L;
}