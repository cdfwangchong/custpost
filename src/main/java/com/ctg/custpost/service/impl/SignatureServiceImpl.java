package com.ctg.custpost.service.impl;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.pojo.dto.CheckInfoDto;
import com.ctg.custpost.pojo.dto.SigPicDto;
import com.ctg.custpost.pojo.until.Result;
import com.ctg.custpost.pojo.until.SigPic;
import com.ctg.custpost.service.SignatureService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.log4j.Logger;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static com.ctg.custpost.pojo.until.Constant.errCode22;

@Service
public class SignatureServiceImpl implements SignatureService {

    @Autowired
    private MongoTemplate mongoTemplate;

    Logger logger = Logger.getLogger(QryTraceServiceImpl.class);

    @Override
    public Result uploadSigPic(SigPicDto sigPicDTO) {
        SigPic sigPic = sigPicDTO2Entity(sigPicDTO);
        SigPic savedFile;
        try {
            savedFile = mongoTemplate.save(sigPic);
        } catch (Exception e) {
            logger.error(sigPicDTO.getFlagOne()+"新增顾客电子签名失败");
            throw new CustPostNotFoundException(errCode22,"新增顾客电子签名失败");
        }
        if(savedFile.getId().isEmpty())
            return new Result(1001,"更新失败",false);
        return new Result(1002,"更新成功",true);
    }

    @Override
    public String qrySign(CheckInfoDto ciDto) {
        //查找条件
        BasicDBList basicDBList = new BasicDBList();
        basicDBList.add(new BasicDBObject("flagOne", ciDto.getIdNum()));
        basicDBList.add(new BasicDBObject("businessType", ciDto.getBusinessType()));

        //封装条件成对象
        DBObject obj = new BasicDBObject();
        obj.put("$and",basicDBList);

        //设置返回结果字段
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fileName",1);
        fieldsObject.put("createdTime",1);

        //条件查询对象+返回结果要求对象=Query
        Query query = new BasicQuery(obj.toString(),fieldsObject.toString());
        List<SigPic> resultSigList = mongoTemplate.find(query,SigPic.class,"sigPic");

        if (resultSigList.isEmpty()) {
            return "";
        } else if (resultSigList.size() == 1) {
            return resultSigList.get(0).getFileName();
        } else {//假如目录下有多个匹配，就根据时间顺序获取最近的，并输出文件名，去后缀
            Collections.sort(resultSigList,Collections.reverseOrder());
            return resultSigList.get(0).getFileName();
        }
    }

    SigPic sigPicDTO2Entity(SigPicDto sigPicDTO){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        String strTimestamp = dateTime.format(fmt);
        SigPic sigPic = new SigPic();
        sigPic.setBusinessType(sigPicDTO.getBusinessType());
        //MAILSIGN+免税店编号+时间+旅客证件号
        if("take".equals(sigPicDTO.getBusinessType()))
            sigPic.setFileName("CHECKSIGN"+sigPicDTO.getFlagTwo()+strTimestamp+sigPicDTO.getFlagOne());
        if("mail".equals(sigPicDTO.getBusinessType()))
            sigPic.setFileName("MAILSIGN"+sigPicDTO.getFlagTwo()+strTimestamp+sigPicDTO.getFlagOne());
        sigPic.setFileName("MAILSIGN"+sigPicDTO.getFlagTwo()+strTimestamp+sigPicDTO.getFlagOne());
        sigPic.setCreatedTime(sigPicDTO.getCreatedTime());
        sigPic.setContent(new Binary(sigPicDTO.getContent()));
        sigPic.setContentType(sigPicDTO.getContentType());
        sigPic.setSize(sigPicDTO.getContent().length);
        sigPic.setFlagOne(sigPicDTO.getFlagOne());
        sigPic.setFlagTwo(sigPicDTO.getFlagTwo());
        sigPic.setFlagThree(sigPicDTO.getFlagThree());
        return sigPic;
    }
}
