package com.ctg.custpost.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CheckCanclePostDao {
    Map isCanclePost(Map<String, String> param);
}
