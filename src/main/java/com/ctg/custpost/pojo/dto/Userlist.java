package com.ctg.custpost.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * USERLIST
 * @author 
 */
@Data
public class Userlist implements Serializable {
    /**
     * 证件类型+证件号
     */
    private String idseq;

    /**
     * 证件类型
     */
    private String idtype;

    /**
     * 证件号
     */
    private String idno;

    /**
     * 状态（Y：正常，L：锁定）
     */
    private String status;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机
     */
    private String telphno;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 微信号
     */
    private String wechatid;

    /**
     * 密码（32位大写的MD5）
     */
    private String password;

    /**
     * 注册日期
     */
    private Date registerdate;

    /**
     * 最后登录时间
     */
    private Date lastlogintime;

    /**
     * 备注
     */
    private String memo;

    private static final long serialVersionUID = 1L;
}