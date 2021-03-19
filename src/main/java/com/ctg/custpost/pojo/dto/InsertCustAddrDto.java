package com.ctg.custpost.pojo.dto;

public class InsertCustAddrDto {

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
        this.rec_provincename = rec_provincename;
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

    public String getRec_postcode() {
        return rec_postcode;
    }

    public void setRec_postcode(String rec_postcode) {
        this.rec_postcode = rec_postcode;
    }

    public String getGwkh() {
        return gwkh;
    }

    public void setGwkh(String gwkh) {
        this.gwkh = gwkh;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getRec_xsdno() {
        return rec_xsdno;
    }

    public void setRec_xsdno(String rec_xsdno) {
        this.rec_xsdno = rec_xsdno;
    }

    public int getRec_pkgcnt() {
        return rec_pkgcnt;
    }

    public void setRec_pkgcnt(int rec_pkgcnt) {
        this.rec_pkgcnt = rec_pkgcnt;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getSeqno_c() {
        return seqno_c;
    }

    public void setSeqno_c(int seqno_c) {
        this.seqno_c = seqno_c;
    }

    private String rec_name;
    private String rec_phoneno;
    private String rec_detailaddress;
    private String rec_provincename;
    private String rec_cityname="";
    private String rec_areaname;
    private String rec_townname="";
    private String rec_postcode;
    private String gwkh;
    private String type;
    private String market;
    private String rec_xsdno;
    private int rec_pkgcnt;
    private String flag;
    private int seqno_c;

}
