package com.ctg.custpost.dao;

import com.ctg.custpost.pojo.dto.Custaddrlist;
import com.ctg.custpost.pojo.dto.InsertCustAddrDto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CustaddrlistDao {

    int insert(List<InsertCustAddrDto> record);

    Custaddrlist selectByPrimaryKey(BigDecimal seqno);

}