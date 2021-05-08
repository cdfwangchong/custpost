package com.ctg.custpost.service.impl;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.dao.CustSignDao;
import com.ctg.custpost.dao.UserlistDao;
import com.ctg.custpost.pojo.dto.CustSignDto;
import com.ctg.custpost.pojo.dto.Userlist;
import com.ctg.custpost.service.CustSignService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

import static com.ctg.custpost.pojo.until.Constant.*;

@Service
public class CustSignServiceImpl implements CustSignService {
    @Autowired
    CustSignDao csDao;
    
    @Autowired
    UserlistDao ulDao;

    Logger logger = Logger.getLogger(PostAddressServiceImpl.class);
    @Override
    public String qrySignature(@RequestBody CustSignDto csDto) {
        String open_id = csDto.getOpen_id();
        String gwkh=csDto.getCard_type()+csDto.getCard_id();
        logger.info("取到电子签名查询接口传入参数"+open_id+"#"+gwkh);

        Userlist ul;
        try {
            ul = ulDao.selectByPrimaryKey(open_id);
        } catch (Exception e) {
            logger.info(gwkh+"未注册，无记录");
            throw new CustPostNotFoundException(errCode7,errMsg7);
        }
        if (ul == null) {
            logger.info(gwkh+"未注册，无记录");
            throw new CustPostNotFoundException(errCode7,errMsg7);
        }
        Map param = new HashMap<String,String>();
        param.put("gwkh",gwkh);
        param.put("open_id",open_id);
        return "Y";//2021/04/22焕哥说电子签名不写入表里
//        int retcnt;
//        try {
//            retcnt = csDao.qryCustSign(param);
//        } catch (Exception e) {
//            logger.error(gwkh+"查询失败");
//            throw new CustPostNotFoundException(errCode,errMsg);
//        }
//        if (retcnt == 0) {
//            logger.info(gwkh+"没有电子签名");
//            return "N";
//        }else {
//            logger.info(gwkh+"已经添加电子签名");
//            return "Y";
//        }
    }

    @Override
    public String updSignature(@RequestBody CustSignDto csDto) {
        String open_id = csDto.getOpen_id();
        String gwkh=csDto.getCard_type()+csDto.getCard_id();

        logger.info("取到电子签名查询接口传入参数"+open_id+"#"+gwkh);
        return "Y";//2021/04/22焕哥说电子签名不写入表里
//        Userlist ul;
//        try {
//            ul = ulDao.selectByPrimaryKey(open_id);
//        } catch (Exception e) {
//            logger.info(gwkh+"未注册，无记录");
//            throw new CustPostNotFoundException(errCode7,errMsg7);
//        }
//        if (ul == null) {
//            logger.info(gwkh+"未注册，无记录");
//            throw new CustPostNotFoundException(errCode7,errMsg7);
//        }
//        Map param = new HashMap();
//        param.put("gwkh",gwkh);
//        param.put("open_id",open_id);
//        param.put("signature",csDto.getSignature());
//        int retcnt;
//        try {
//            retcnt = csDao.updCustSign(param);
//        } catch (Exception e) {
//            logger.error(gwkh+"更新失败");
//            throw new CustPostNotFoundException(errCode,errMsg);
//        }
//        if (retcnt == 0) {
//            logger.info(gwkh+"更新失败");
//            return "N";
//        }else {
//            logger.info(gwkh+"更新成功");
//            return "Y";
//        }
    }
}
