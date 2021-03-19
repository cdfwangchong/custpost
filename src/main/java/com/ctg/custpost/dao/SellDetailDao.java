package com.ctg.custpost.dao;

import com.ctg.custpost.pojo.dto.PickBillDto;
import com.ctg.custpost.pojo.dto.PickNumDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellDetailDao {
    List<PickBillDto> QrySellDetail(PickNumDto picknumdto);
}
