package com.ctg.custpost.pojo.dto;

import com.ctg.custpost.pojo.until.BillEntity;

import java.util.List;

public class InsertCustAddrAndListDto {
    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getRec_name() {
        return rec_name;
    }

    public void setRec_name(String rec_name) {
        this.rec_name = rec_name==null?"":rec_name.trim();
    }

    public String getRec_detailaddress() {
        return rec_detailaddress;
    }

    public void setRec_detailaddress(String rec_detailaddress) {
        this.rec_detailaddress = rec_detailaddress;
    }

    public String getRec_provincename() {
        return rec_provincename;
    }

    public void setRec_provincename(String rec_provincename) {
        this.rec_provincename = rec_provincename==null?"":rec_provincename.trim();
    }

    public String getRec_cityname() {
        return rec_cityname;
    }

    public void setRec_cityname(String rec_cityname) {
        this.rec_cityname = rec_cityname;
    }

    public String getRec_areaname() {
        return rec_areaname;
    }

    public void setRec_areaname(String rec_areaname) {
        this.rec_areaname = rec_areaname;
    }

    public String getRec_townname() {
        return rec_townname;
    }

    public void setRec_townname(String rec_townname) {
        this.rec_townname = rec_townname;
    }

    public List<BillEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<BillEntity> orderList) {
        this.orderList = orderList;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(int seq_no) {
        this.seq_no = seq_no;
    }

    private String open_id;
    private String rec_name;
    private String rec_phoneno;
    private String rec_detailaddress;
    private String rec_provincename;
    private String rec_cityname;
    private String rec_areaname;
    private String rec_townname;
    private String rec_postcode;
    private String type;
    private int seq_no;
    List<BillEntity> orderList;

}
