package com.ctg.custpost.controller;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.pojo.dto.CustSignDto;
import com.ctg.custpost.pojo.until.Result;
import com.ctg.custpost.service.CustSignService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ctg.custpost.pojo.until.Constant.*;
import static com.ctg.custpost.pojo.until.Constant.sucMsg;

@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class CustSignController {
    Logger logger = Logger.getLogger(CustSignController.class);
    @Autowired
    CustSignService csService = null;

    @PostMapping("qrySignature")
    @ResponseBody
    public Result<String> qrySignature(@RequestBody CustSignDto csDto) {
        if (csDto.getOpen_id() == null) {
            logger.error("传入的OPENID为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }
        if (csDto.getCard_id() == null) {
            logger.error("传入的证件号为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }
        if (csDto.getCard_type() == null) {
            logger.error("传入的证件类型为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }

        String ret = csService.qrySignature(csDto);
        return new Result<String>(sucCode,sucMsg,ret);
    }

    @PostMapping("updSignature")
    @ResponseBody
    public Result<String> updSignature(@RequestBody CustSignDto csDto) {
        if (csDto.getOpen_id() == null) {
            logger.error("传入的OPENID为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }
        if (csDto.getCard_id() == null) {
            logger.error("传入的证件号为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }
        if (csDto.getCard_type() == null) {
            logger.error("传入的证件类型为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }
        if (csDto.getSignature() == null) {
            logger.error("传入的电子签名为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }

        String ret = csService.updSignature(csDto);
        if ("Y".equals(ret)) {
            logger.info(csDto.getCard_id()+"更新成功");
            return new Result<String>(sucCode,sucMsg,ret);
        }else {
            logger.error(csDto.getCard_id()+"更新失败");
            throw new CustPostNotFoundException(errCode,errMsg_1);
        }
    }
}
