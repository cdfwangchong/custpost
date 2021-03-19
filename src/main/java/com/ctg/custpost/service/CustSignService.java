package com.ctg.custpost.service;

import com.ctg.custpost.pojo.dto.CustSignDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustSignService {
    String qrySignature(CustSignDto csDto);

    String updSignature(CustSignDto csDto);
}
