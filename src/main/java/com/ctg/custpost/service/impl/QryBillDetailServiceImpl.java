package com.ctg.custpost.service.impl;

import com.ctg.custpost.dao.SellDetailDao;
import com.ctg.custpost.pojo.dto.PickBillDto;
import com.ctg.custpost.pojo.dto.PickNumDto;
import com.ctg.custpost.service.QryBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QryBillDetailServiceImpl implements QryBillDetailService {
    @Autowired
    SellDetailDao selldetaildao;

    @Override
    public List<PickBillDto> getselldetail(PickNumDto picknumdto) {
        return selldetaildao.QrySellDetail(picknumdto);
    }
}
