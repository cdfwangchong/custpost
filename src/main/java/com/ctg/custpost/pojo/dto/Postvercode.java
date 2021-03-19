package com.ctg.custpost.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * POSTVERCODE
 * @author 
 */
@Data
public class Postvercode implements Serializable {
    /**
     * 序号
     */
    private BigDecimal seqno;

    private String openid;

    /**
     * 购物卡号
     */
    private String gwkh;

    /**
     * 发送日期
     */
    private Date fstime;

    /**
     * 验证码
     */
    private String vercode;

    /**
     * 电话
     */
    private String telnum;

    private static final long serialVersionUID = 1L;
}