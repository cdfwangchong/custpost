package com.ctg.custpost.service;

import com.ctg.custpost.pojo.dto.WaybilltraceDto;

import java.util.List;

public interface QryTranceService {
    List<WaybilltraceDto> QryWaybilltrace(String trance);
}
