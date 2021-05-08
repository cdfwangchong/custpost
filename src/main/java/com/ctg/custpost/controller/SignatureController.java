package com.ctg.custpost.controller;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.pojo.dto.CheckInfoDto;
import com.ctg.custpost.pojo.dto.SigPicDto;
import com.ctg.custpost.pojo.until.Result;
import com.ctg.custpost.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ctg.custpost.pojo.until.Constant.*;
import static com.ctg.custpost.pojo.until.Constant.errMsg19;

/**
 * project 把电子签名写入MONGODB里
 *
 * author wangc
 *
 * time 2021/4/27
 */
@RestController
@RequestMapping("/cdfg")
public class SignatureController {
    @Autowired
    SignatureService signatureService;

    @PostMapping("/updSign")
    @ResponseBody
    Result login(@RequestBody SigPicDto sigPicDTO) {
        return signatureService.uploadSigPic(sigPicDTO);
    }

    @PostMapping("/qrySign")
    @ResponseBody
    Result<String> qrySign(@RequestBody CheckInfoDto ciDto) {
        String result=signatureService.qrySign(ciDto);
        if(result == null) {
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }else {
            return new Result<String>(sucCode,sucMsg,result);
        }
    }
}
