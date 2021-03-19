package com.ctg.custpost.service;

import com.ctg.custpost.pojo.dto.PostaddressDto;
import com.ctg.custpost.pojo.until.Login;

public interface PostAddressService {
    PostaddressDto qryPostAddress(Login login);

    int insertPostAddress(PostaddressDto ipaDto);

    int updatePostAddress(PostaddressDto ipaDto);
}
