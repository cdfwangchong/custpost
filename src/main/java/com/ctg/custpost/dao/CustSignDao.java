package com.ctg.custpost.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CustSignDao {
    int qryCustSign(Map param);

    int updCustSign(Map param);
}
