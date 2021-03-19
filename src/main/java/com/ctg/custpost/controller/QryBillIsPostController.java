package com.ctg.custpost.controller;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.pojo.dto.XsdnoDto;
import com.ctg.custpost.pojo.until.BillEntity;
import com.ctg.custpost.pojo.until.CustAddrlistEntity;
import com.ctg.custpost.pojo.until.Login;
import com.ctg.custpost.pojo.until.Result;
import com.ctg.custpost.service.QryBillIsPostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ctg.custpost.pojo.until.Constant.*;

/*
 * project name :自助邮寄
 * for:未邮寄提货单接口和已经邮寄的提货单接口
 * author：wangc
 * time：2020-10-10
 * */
@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class QryBillIsPostController {

    @Autowired
    private QryBillIsPostService qbipService=null;
    Logger logger = Logger.getLogger(QryBillIsPostController.class);

    /**
     * 未邮寄提货单接口
     * @param login
     * @return
     */
    @PostMapping("/qrynotpostbill")
    @ResponseBody
    public Result<XsdnoDto> qryNotPostBill(@RequestBody Login login) {
        XsdnoDto xsdnoDto;

        if (login == null){
            logger.error("邮寄提货单查询接口传入的参数值为null");
            throw new CustPostNotFoundException(errCode5,errMsg5);
        }

        xsdnoDto = qbipService.qryNotPostBill(login);
        for (int i = 0; i < xsdnoDto.getOrderList().size(); i++) {
            BillEntity be = xsdnoDto.getOrderList().get(i);
            logger.info("取到未邮寄提货单接口返回值："+be.getMarket()+"#"+be.getShxsdno()+"#"+be.getShoughtpay());
        }

        return new Result<XsdnoDto>(sucCode,sucMsg,xsdnoDto);
    }

    /**
     * 邮寄提货单查询接口
     * @param login
     * @return
     */
    @PostMapping("/qrypostbill")
    @ResponseBody
    public Result<List<CustAddrlistEntity>> qryPostBill(@RequestBody Login login) {
        List<CustAddrlistEntity> beList;

        if (login == null){
            logger.error("邮寄提货单查询接口传入的参数值为null");
            throw new CustPostNotFoundException(errCode5,errMsg5);
        }

        beList = qbipService.qryPostBill(login);
        for (int i = 0; i < beList.size(); i++) {
            CustAddrlistEntity be = new CustAddrlistEntity();
            be = beList.get(i);
            String address = be.getRec_provincename()+be.getRec_cityname()+be.getRec_areaname()+be.getRec_townname()+be.getRec_detailaddress();
            logger.info("取到邮寄提货单接口返回值："+be.getRec_name()+"#"+be.getRec_xsdno()+"#"+be.getSeq_no()+address);
        }

        return new Result<List<CustAddrlistEntity>>(sucCode,sucMsg,beList);
    }
}
