package com.ctg.custpost.service;

import com.ctg.custpost.pojo.dto.XsdnoDto;
import com.ctg.custpost.pojo.until.CustAddrlistEntity;
import com.ctg.custpost.pojo.until.Login;

import java.util.List;


public interface QryBillIsPostService {

    XsdnoDto qryNotPostBill(Login login);

    List<CustAddrlistEntity> qryPostBill(Login login);

}
