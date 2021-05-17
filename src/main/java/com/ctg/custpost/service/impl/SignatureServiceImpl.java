package com.ctg.custpost.service.impl;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.pojo.dto.CheckInfoDto;
import com.ctg.custpost.pojo.dto.SigPicDto;
import com.ctg.custpost.pojo.until.Result;
import com.ctg.custpost.pojo.until.SigPic;
import com.ctg.custpost.pojo.until.UploadFile;
import com.ctg.custpost.pojo.until.UploadPicture;
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
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.ctg.custpost.pojo.until.Constant.errCode22;

@Service
public class SignatureServiceImpl implements SignatureService {

    @Autowired
    private MongoTemplate mongoTemplate;

    Logger logger = Logger.getLogger(QryTraceServiceImpl.class);

    public static final String BUSINESS_TYPE_TAKE = "take";
    public static final String BUSINESS_TYPE_MAIL = "mail";
    public static final String PIC_FACE = "face";
    public static final String PIC_SIG = "sign";

    @Override
    @Transactional
    public Result uploadSigPic(SigPicDto sigPicDTO) {
        SigPic sigPicTake = sigPicDTO2Entity(sigPicDTO);
        SigPic sigPicMail = sigPicDTO2Entity(sigPicDTO);
        sigPicTake.setBusinessType(BUSINESS_TYPE_TAKE);
        sigPicMail.setBusinessType(BUSINESS_TYPE_MAIL);

        if(!send2DB(sigPicTake).getId().isEmpty()&&!send2DB(sigPicMail).getId().isEmpty())
            return new Result(1002,"更新成功",true);
        return new Result(1001,"更新失败",false);
    }

    @Override
    public String qrySign(CheckInfoDto ciDto) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        String strTimestamp = dateTime.format(fmt);
        String checkType = ciDto.getBusinessType();//判断是查询电子签名还是人证核验

        //查找条件
        BasicDBList basicDBList = new BasicDBList();
        basicDBList.add(new BasicDBObject("flagOne", ciDto.getIdNum()));
        basicDBList.add(new BasicDBObject("businessType", BUSINESS_TYPE_MAIL));
        basicDBList.add(new BasicDBObject("flagThree", strTimestamp));

        //封装条件成对象
        DBObject obj = new BasicDBObject();
        obj.put("$and",basicDBList);

        //设置返回结果字段
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("fileName",1);
        fieldsObject.put("createdTime",1);
        List<UploadPicture> picList = new ArrayList<>();
        Query query = new BasicQuery(obj.toString(),fieldsObject.toString());

        if (PIC_SIG.equals(checkType)) {
            //条件查询对象+返回结果要求对象=Query
            List<SigPic> resultSigList = mongoTemplate.find(query, SigPic.class,"sigPic");
            picList.addAll(resultSigList);
        }
        if (PIC_FACE.equals(checkType)) {
            //条件查询对象+返回结果要求对象=Query
            List<UploadFile> resultSigList = mongoTemplate.find(query, UploadFile.class,"uploadFile");
            picList.addAll(resultSigList);
        }

        if (picList.isEmpty()) {
            return "";
        } else if (picList.size() == 1) {
            return picList.get(0).getFileName();
        } else {//假如目录下有多个匹配，就根据时间顺序获取最近的，并输出文件名，去后缀
            Collections.sort(picList,Collections.reverseOrder());
            return picList.get(0).getFileName();
        }
    }

    SigPic sigPicDTO2Entity(SigPicDto sigPicDTO){
        logger.info(sigPicDTO.getContent());
        byte [] images=null;//返回图像
        try {
            String content = sigPicDTO.getContent().substring(22);
            images=new BASE64Decoder().decodeBuffer(content.trim());//Base64转换成byte数组
        } catch (IOException e) {
            logger.error("电子签名转换成输出流异常");
        }
        SigPic sigPic = new SigPic();
        sigPic.setCreatedTime(sigPicDTO.getCreatedTime());
        sigPic.setContent(new Binary(images));
        sigPic.setContentType(sigPicDTO.getContentType());
        sigPic.setSize(images.length);
        sigPic.setFlagOne(sigPicDTO.getFlagOne());
        sigPic.setFlagTwo(sigPicDTO.getFlagTwo());
        sigPic.setFlagThree(sigPicDTO.getFlagThree());
        return sigPic;
    }

    public SigPic send2DB(SigPic sigPic){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        String strTimestamp = dateTime.format(fmt);

        //MAILSIGN+免税店编号+时间+旅客证件号
        if(BUSINESS_TYPE_TAKE.equals(sigPic.getBusinessType()))
            sigPic.setFileName("CHECKSIGN"+sigPic.getFlagTwo()+strTimestamp+sigPic.getFlagOne());
        if(BUSINESS_TYPE_MAIL.equals(sigPic.getBusinessType()))
            sigPic.setFileName("MAILSIGN"+sigPic.getFlagTwo()+strTimestamp+sigPic.getFlagOne());
        SigPic savedFile;
        try {
            savedFile = mongoTemplate.save(sigPic);
        } catch (Exception e) {
            logger.error(sigPic.getFlagOne()+"新增顾客电子签名失败");
            throw new CustPostNotFoundException(errCode22,"新增顾客电子签名失败");
        }
        return savedFile;
    }

}
