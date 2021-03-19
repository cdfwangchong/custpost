package com.ctg.custpost.dao;

import com.ctg.custpost.pojo.dto.Postvercode;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
*@author wangchong 
*2018
*/
@Repository
public interface UserDao {

	Map<String, String> registerUser(Map<String, String> param);
	
	Map<String, String> login(Map<String, String> param);
	
	Map<String, String> getVercode(Map<String, String> param);
	
	Map<String, String> weChat(Map<String, String> param);

    int insert(Postvercode pv);

    Postvercode selectByPrimaryKey(Map<String, String> param);
}
