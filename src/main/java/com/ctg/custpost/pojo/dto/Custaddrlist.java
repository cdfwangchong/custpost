package com.ctg.custpost.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * CUSTADDRLIST
 * @author 
 */
@Data
public class Custaddrlist implements Serializable {
    /**
     * 序号
     */
    private BigDecimal seqno;

    /**
     * 购物卡号
     */
    private String gwkh;

    /**
     * 收件人姓名
     */
    private String recname;

    /**
     * 收件人手机
     */
    private String recphoneno;

    /**
     * 邮编
     */
    private String recpostcode;

    /**
     * 省名称
     */
    private String recprovincename;

    /**
     * 市名称(如果是省管区县，则为空)
     */
    private String reccityname;

    /**
     * 区名称
     */
    private String recareaname;

    /**
     * 街道/镇名称
     */
    private String rectownname;

    /**
     * 剩余详细地址信息
     */
    private String recdetailaddress;

    /**
     * 申请类型：1申请邮寄单 2取消邮寄单
     */
    private String type;

    /**
     * 申请门店
     */
    private String market;

    /**
     * 申请提货单号,用| 分隔
     */
    private String recxsdno;

    /**
     * 申请包裹个数
     */
    private BigDecimal recpkgcnt;

    /**
     * 0未处理 1确认中(部分确认) 2已确认 3已获取邮寄资格 9已完结(发送海关报文，取消邮寄的也是自动完结)
     */
    private String flag;

    /**
     * 错误代码
     */
    private BigDecimal errno;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 邮寄资格获取人
     */
    private String zghqr;

    /**
     * 邮寄资格获取时间
     */
    private String zghqsj;

    /**
     * 邮寄资格获取报文内容
     */
    private String zghqbw;

    /**
     * 参与邮寄提货单号,用| 分隔
     */
    private String yjxsdno;

    /**
     * 参与邮寄提货单个数
     */
    private BigDecimal yjcnt;

    /**
     * 运单号,快递的回执
     */
    private String traceno;

    private static final long serialVersionUID = 1L;
}