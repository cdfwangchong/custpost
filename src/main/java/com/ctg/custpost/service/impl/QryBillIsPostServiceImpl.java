package com.ctg.custpost.service.impl;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.dao.QryBillIsPostDao;
import com.ctg.custpost.pojo.dto.XsdnoDto;
import com.ctg.custpost.pojo.until.BillEntity;
import com.ctg.custpost.pojo.until.CustAddrlistEntity;
import com.ctg.custpost.pojo.until.Login;
import com.ctg.custpost.service.QryBillIsPostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ctg.custpost.pojo.until.Constant.*;

@Service
public class QryBillIsPostServiceImpl implements QryBillIsPostService {
    @Autowired
    private QryBillIsPostDao qbipDao=null;
    Logger logger = Logger.getLogger(QryBillIsPostServiceImpl.class);

    /**
     * 未邮寄提货单接口
     * @param login
     * @return
     */
    @Override
    public XsdnoDto qryNotPostBill(Login login) {
        if (login.getOpen_id() == null) {
            logger.error("传入的OPENID为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }
        List<BillEntity> beList;
        String ret_card;
        String ret_name;
        Map param = new HashMap<String,String>();
        XsdnoDto xsdnoDto = new XsdnoDto();
        try {
            param.put("openId",login.getOpen_id());
            qbipDao.qryNotPostBill(param);
            //取出结果集
            beList = (List<BillEntity>) param.get("wyjRc");
            ret_name = (String)param.get("ret_name");
            ret_card = (String)param.get("ret_card");

            xsdnoDto.setOrderList(beList);
            xsdnoDto.setRet_card(ret_card);
            xsdnoDto.setRet_name(ret_name);
        } catch (Exception e) {
            logger.error("查找未邮寄的提货单存储过程返回值异常");
            throw new CustPostNotFoundException(errCode,errMsg);
        }
        //取出ret_flag
        String ret_flag = (String) param.get("ret_flag");
        String ret_msg = (String) param.get("ret_msg");
        if (!"1".equals(ret_flag)) {
            logger.error("用户"+login.getOpen_id()+ret_msg);
            throw new CustPostNotFoundException(errCode6,ret_msg);
        }
        return xsdnoDto;
    }

    /**
     * 邮寄提货单查询接口
     * @param login
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 30,rollbackFor = Exception.class)
    @Override
    public List<CustAddrlistEntity> qryPostBill(Login login) {
        Map param = new HashMap<String,String>();
        param.put("openId",login.getOpen_id());
        List<CustAddrlistEntity> beyList;
        try {
            qbipDao.qryPostBill(param);

            //取出结果集
            beyList = (List<CustAddrlistEntity>) param.get("yjRc");
        } catch (Exception e) {
            logger.error("查找已邮寄的提货单存储过程返回值异常");
            throw new CustPostNotFoundException(errCode,errMsg);
        }
        return beyList;
    }
}
