package com.ctg.custpost.dao;

import com.ctg.custpost.pojo.dto.Userlist;
import org.springframework.stereotype.Repository;

@Repository
public interface UserlistDao {

    Userlist selectByPrimaryKey(String openId);

}