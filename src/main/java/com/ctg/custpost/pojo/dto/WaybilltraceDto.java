package com.ctg.custpost.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class WaybilltraceDto {
    public BigDecimal getSEQNO() {
        return SEQNO;
    }

    public void setSEQNO(BigDecimal SEQNO) {
        this.SEQNO = SEQNO;
    }

    public String getTRACENO() {
        return TRACENO;
    }

    public void setTRACENO(String TRACENO) {
        this.TRACENO = TRACENO;
    }

    public Date getOPTIME() {
        return OPTIME;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public void setOPTIME(Date OPTIME) {
        this.OPTIME = OPTIME;
    }

    public String getOPCODE() {
        return OPCODE;
    }

    public void setOPCODE(String OPCODE) {
        this.OPCODE = OPCODE;
    }

    public String getOPNAME() {
        return OPNAME;
    }

    public void setOPNAME(String OPNAME) {
        this.OPNAME = OPNAME;
    }

    public String getOPDESC() {
        return OPDESC;
    }

    public void setOPDESC(String OPDESC) {
        this.OPDESC = OPDESC;
    }

    public String getOPORGPROVNAME() {
        return OPORGPROVNAME;
    }

    public void setOPORGPROVNAME(String OPORGPROVNAME) {
        this.OPORGPROVNAME = OPORGPROVNAME;
    }

    public String getOPORGCITY() {
        return OPORGCITY;
    }

    public void setOPORGCITY(String OPORGCITY) {
        this.OPORGCITY = OPORGCITY;
    }

    public String getOPORGCODE() {
        return OPORGCODE;
    }

    public void setOPORGCODE(String OPORGCODE) {
        this.OPORGCODE = OPORGCODE;
    }

    public String getOPORGNAME() {
        return OPORGNAME;
    }

    public void setOPORGNAME(String OPORGNAME) {
        this.OPORGNAME = OPORGNAME;
    }

    public String getOPERATORNO() {
        return OPERATORNO;
    }

    public void setOPERATORNO(String OPERATORNO) {
        this.OPERATORNO = OPERATORNO;
    }

    public String getOPERATORNAME() {
        return OPERATORNAME;
    }

    public void setOPERATORNAME(String OPERATORNAME) {
        this.OPERATORNAME = OPERATORNAME;
    }

    private BigDecimal SEQNO;

    private String TRACENO;

    private Date OPTIME;

    private String OPCODE="";

    private String OPNAME="";

    private String OPDESC="";

    private String OPORGPROVNAME="";

    private String OPORGCITY="";

    private String OPORGCODE="";

    private String OPORGNAME="";

    private String OPERATORNO="";

    private String OPERATORNAME="";
}
