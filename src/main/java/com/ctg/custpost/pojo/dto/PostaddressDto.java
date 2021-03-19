package com.ctg.custpost.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * POSTADDRESS
 * @author 
 */

public class PostaddressDto implements Serializable {
    public String getGwkh() {
        return gwkh;
    }

    public void setGwkh(String gwkh) {
        this.gwkh = gwkh;
    }

    public String getRec_name() {
        return rec_name;
    }

    public void setRec_name(String rec_name) {
        this.rec_name = rec_name;
    }

    public String getRec_phoneno() {
        return rec_phoneno;
    }

    public void setRec_phoneno(String rec_phoneno) {
        this.rec_phoneno = rec_phoneno;
    }

    public String getRec_postcode() {
        return rec_postcode;
    }

    public void setRec_postcode(String rec_postcode) {
        this.rec_postcode = rec_postcode;
    }

    public String getRec_provincename() {
        return rec_provincename;
    }

    public void setRec_provincename(String rec_provincename) {
        this.rec_provincename = rec_provincename;
    }

    public String getRec_cityname() {
        return rec_cityname;
    }

    public void setRec_cityname(String rec_cityname) {
        this.rec_cityname = rec_cityname == null?"":rec_cityname.trim();
    }

    public String getRec_areaname() {
        return rec_areaname;
    }

    public void setRec_areaname(String rec_areaname) {
        this.rec_areaname = rec_areaname;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag==null?"":deleteFlag.trim();
    }

    public Date getFsdate() {
        return fsdate;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public void setFsdate(Date fsdate) {
        this.fsdate = fsdate;
    }

    public String getRec_townname() {
        return rec_townname;
    }

    public void setRec_townname(String rec_townname) {
        this.rec_townname = rec_townname== null?"":rec_townname.trim();
    }

    public String getRec_detailaddress() {
        return rec_detailaddress;
    }

    public void setRec_detailaddress(String rec_detailaddress) {
        this.rec_detailaddress = rec_detailaddress;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id== null?"":open_id.trim();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 购物卡号
     */
    private String gwkh;

    /**
     * 收件人
     */
    private String rec_name;

    /**
     * 收件人电话号码
     */
    private String rec_phoneno;

    /**
     * 邮编
     */
    private String rec_postcode;

    /**
     * 省名称
     */
    private String rec_provincename;

    /**
     * 市名称（省管区县，则为空）
     */
    private String rec_cityname="";

    /**
     * 区名称
     */
    private String rec_areaname;

    /**
     * 是否启用
     */
    private String deleteFlag;

    /**
     * 更新日期
     */
    private Date fsdate;

    /**
     * 镇/街道名称
     */
    private String rec_townname="";

    /**
     * 剩余详细地址
     */
    private String rec_detailaddress;

    /**
     * open_id
     */
    private String open_id;

    private static final long serialVersionUID = 1L;
}