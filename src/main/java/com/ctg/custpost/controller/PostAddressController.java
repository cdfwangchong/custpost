package com.ctg.custpost.controller;

import com.ctg.custpost.pojo.dto.PostaddressDto;
import com.ctg.custpost.pojo.until.Login;
import com.ctg.custpost.pojo.until.Result;
import com.ctg.custpost.service.PostAddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ctg.custpost.pojo.until.Constant.sucCode;
import static com.ctg.custpost.pojo.until.Constant.sucMsg;

/*
 * project name :自助邮寄
 * for:收货地址管理和新增接口
 * author：wangc
 * time：2020-10-10
 * */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class PostAddressController {
    @Autowired
    PostAddressService paService = null;
    Logger logger = Logger.getLogger(PostAddressController.class);

    /**
     * 收货地址管理接口
     * @param login
     * @return
     */
    @PostMapping("qryPostAddress")
    @ResponseBody
    public Result<PostaddressDto> qryPostAddress(@RequestBody Login login) {
        PostaddressDto pd = paService.qryPostAddress(login);
        logger.info("取到收货地址管理接口的传入参数"+login.getOpen_id());

        return new Result<PostaddressDto>(sucCode,sucMsg,pd);
    }

    /**
     * 收货地址新增接口
     * @param paDto
     * @return
     */
    @PostMapping("insertPostAddress")
    @ResponseBody
    public Result<String> insertPostAddress(@RequestBody PostaddressDto paDto) {
        String address = paDto.getRec_provincename()+paDto.getRec_cityname()+paDto.getRec_areaname()+paDto.getRec_townname()+paDto.getRec_detailaddress();
        logger.info("取到收货地址新增接口的传入参数"+paDto.getOpen_id()+"详细地址："+address);
        int rs = paService.insertPostAddress(paDto);

        return new Result<String>(sucCode,sucMsg,"");
    }

    @PostMapping("updatePostAddress")
    @ResponseBody
    public Result<String> updatePostAddress(@RequestBody PostaddressDto paDto) {
        String address = paDto.getRec_provincename()+paDto.getRec_cityname()+paDto.getRec_areaname()+paDto.getRec_townname()+paDto.getRec_detailaddress();
        logger.info("取到收货地址更新接口的传入参数"+paDto.getOpen_id()+"详细地址："+address);
        int rs = paService.updatePostAddress(paDto);

        return new Result<String>(sucCode,sucMsg,"");
    }
}
