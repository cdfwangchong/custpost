package com.ctg.custpost.pojo.dto;

public class UnbundlingDto {

    private String open_id;

    private String cust_name;

    private String tel_num;

    private String card_id;

    private String pre_telnum;

    private String pre_cardid;

    private String remark;

    private String card_type;

    private String pre_cardtype;

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getPre_cardtype() {
        return pre_cardtype;
    }

    public void setPre_cardtype(String pre_cardtype) {
        this.pre_cardtype = pre_cardtype;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
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

    public String getPre_telnum() {
        return pre_telnum;
    }

    public void setPre_telnum(String pre_telnum) {
        this.pre_telnum = pre_telnum;
    }

    public String getPre_cardid() {
        return pre_cardid;
    }

    public void setPre_cardid(String pre_cardid) {
        this.pre_cardid = pre_cardid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
