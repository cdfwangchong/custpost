package com.ctg.custpost.service;

import com.ctg.custpost.pojo.dto.PickBillDto;
import com.ctg.custpost.pojo.dto.PickNumDto;

import java.util.List;

public interface QryBillDetailService {

    List<PickBillDto> getselldetail(PickNumDto picknumdto);
}
