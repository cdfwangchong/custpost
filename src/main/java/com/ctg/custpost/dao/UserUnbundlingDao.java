package com.ctg.custpost.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserUnbundlingDao {
    Map userUnbundling(Map param);
}
