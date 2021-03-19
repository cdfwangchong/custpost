package com.ctg.custpost.controller;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.pojo.dto.TracenoDto;
import com.ctg.custpost.pojo.dto.WaybilltraceDto;
import com.ctg.custpost.pojo.until.Result;
import com.ctg.custpost.service.QryTranceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ctg.custpost.pojo.until.Constant.*;

@CrossOrigin
@RestController
@RequestMapping("/cdfg")
public class QryTraceController {
    @Autowired
    QryTranceService qtService = null;
    Logger logger = Logger.getLogger(QryTraceController.class);

    @PostMapping("/qryWaybilltrace")
    @ResponseBody
    public Result<List<WaybilltraceDto>> QryWaybilltrace(@RequestBody TracenoDto wbtDto) {
        String traceno = wbtDto.getTraceno();
        if (traceno == null) {
            logger.error("传入的快递单为空");
            throw new CustPostNotFoundException(errCode19,errMsg19);
        }
        logger.info("运单轨迹查询接口传入值："+traceno);
        List<WaybilltraceDto> beList;
        beList = qtService.QryWaybilltrace(traceno);
        return new Result<List<WaybilltraceDto>>(sucCode,sucMsg,beList);
    }
}
