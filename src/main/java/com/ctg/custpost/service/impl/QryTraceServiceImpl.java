package com.ctg.custpost.service.impl;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.dao.QrytraceDao;
import com.ctg.custpost.pojo.dto.WaybilltraceDto;
import com.ctg.custpost.service.QryTranceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ctg.custpost.pojo.until.Constant.*;

@Service
public class QryTraceServiceImpl implements QryTranceService {

    @Autowired
    private QrytraceDao qrytranceDao = null;
    Logger logger = Logger.getLogger(QryTraceServiceImpl.class);
    @Override
    public List<WaybilltraceDto> QryWaybilltrace(String trance) {
        if (trance==null) {
            logger.error("传入的快递单为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }
        Map param = new HashMap<String,String>();
        List<WaybilltraceDto> beList;
        String ret_flag=null;
        String ret_msg=null;
        try {
            param.put("traceno",trance);
            qrytranceDao.QryWaybilltrace(param);
            //取出结果集
            beList = (List<WaybilltraceDto>) param.get("traceRc");
            ret_flag = (String)param.get("ret_flag");
            ret_msg = (String)param.get("ret_msg");
            if (!"1002".equals(ret_flag)) {
                logger.error("查询运单轨迹失败");
                throw new CustPostNotFoundException(errCode,ret_msg);
            }
            if (beList.isEmpty()) {
                logger.error("返回的运单轨迹结果集为空");
                throw new CustPostNotFoundException(errCode21,errMsg21);
            }
        } catch (Exception e) {
            logger.error("查询运单轨迹存储过程返回值异常");
            throw new CustPostNotFoundException(errCode22,errMsg22);
        }
        logger.info("ret_flag"+ret_flag+" ret_msg："+ret_msg);
        return beList;
    }
}
