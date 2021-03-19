package com.ctg.custpost.service;

import com.ctg.custpost.pojo.dto.UnbundlingDto;

import java.util.Map;

public interface UserunbundlingService {

    Map<String, String> userUnbundling(UnbundlingDto unbundlingDto);
}
