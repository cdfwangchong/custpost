package com.ctg.custpost.controller;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.pojo.dto.UnbundlingDto;
import com.ctg.custpost.pojo.until.Result;
import com.ctg.custpost.service.UserunbundlingService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.ctg.custpost.pojo.until.Constant.*;

/*
 * project name :自助邮寄
 * for:用户解绑
 * author：wangc
 * time：2021-3-26
 * */
@Controller
@RequestMapping("/cdfg")
@CrossOrigin
public class UserUnbundlingController {
    @Autowired
    UserunbundlingService uuservice;
    Logger logger = Logger.getLogger(UserUnbundlingController.class);

    @PostMapping("/updateUserinfo")
    @ResponseBody
    public Result<String> userUnbundling(@RequestBody UnbundlingDto unbundlingDto) {
        if (unbundlingDto == null) {
            logger.error("传入的解绑信息对象为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }
        Map<String,String> retmap = uuservice.userUnbundling(unbundlingDto);
        String ret_flag = retmap.get("ret_flag");
        String ret_msg = retmap.get("ret_msg");
        if ("1001".equals(ret_flag)) {
            logger.error(ret_msg);
            throw new CustPostNotFoundException(errCode,ret_msg);
        }else {
            return new Result<String>(sucCode,sucMsg,"");
        }
    }
}
