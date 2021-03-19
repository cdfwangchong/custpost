package com.ctg.custpost.controller;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.pojo.dto.InsertCustAddrAndListDto;
import com.ctg.custpost.pojo.until.BillEntity;
import com.ctg.custpost.pojo.until.Result;
import com.ctg.custpost.service.CustAddrListService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ctg.custpost.pojo.until.Constant.*;

/*
 * project name :自助邮寄
 * for:商品邮寄接口
 * author：wangc
 * time：2020-10-10
 *
 * */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class CustAddrListController {
    @Autowired
    CustAddrListService calService = null;
    Logger logger = Logger.getLogger(CustAddrListController.class);

    @PostMapping("insertCustAddr")
    @ResponseBody
    public Result<String> insertCustAddrList(@RequestBody InsertCustAddrAndListDto ica){
        //详细地址
        String address = ica.getRec_provincename()+ica.getRec_cityname()+ica.getRec_areaname()+ica.getRec_townname()+ica.getRec_detailaddress();
        logger.info("取到要邮寄的详细地址："+address);
        for (int i = 0; i < ica.getOrderList().size(); i++) {
            BillEntity pi = ica.getOrderList().get(i);
            logger.info("取到要邮寄的提货单："+pi.getShxsdno()+"门店："+pi.getMarket());
        }
        boolean bl = calService.insertCustAddrList(ica);
        if (bl) {
            return new Result<String>(sucCode,sucMsg,"");
        }else {
            throw new CustPostNotFoundException(errCode3,errMsg3);
        }
    }
}
