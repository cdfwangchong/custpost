package com.ctg.custpost.service;

import com.ctg.custpost.pojo.dto.CheckInfoDto;
import com.ctg.custpost.pojo.dto.SigPicDto;
import com.ctg.custpost.pojo.until.Result;

public interface SignatureService {
    Result uploadSigPic(SigPicDto sigPicDTO);

    String qrySign(CheckInfoDto ciDto);
}
