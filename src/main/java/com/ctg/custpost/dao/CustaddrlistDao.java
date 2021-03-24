package com.ctg.custpost.dao;

import com.ctg.custpost.pojo.dto.InsertCustAddrDto;
import com.ctg.custpost.pojo.until.BillEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustaddrlistDao {
    int updateByPrimaryKey(List<BillEntity> record);

    int insert(List<InsertCustAddrDto> record);
}