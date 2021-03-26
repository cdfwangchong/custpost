package com.ctg.custpost.service.impl;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.dao.PostaddressDao;
import com.ctg.custpost.dao.UserlistTwoDao;
import com.ctg.custpost.pojo.dto.PostaddressDto;
import com.ctg.custpost.pojo.dto.Userlist;
import com.ctg.custpost.pojo.until.Login;
import com.ctg.custpost.service.PostAddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.ctg.custpost.pojo.until.Constant.*;

@Service
public class PostAddressServiceImpl implements PostAddressService {

    @Autowired
    PostaddressDao paDao;

    @Autowired
    UserlistTwoDao ulDao;

    Logger logger = Logger.getLogger(PostAddressServiceImpl.class);

    /**
     * 查询邮寄地址
     * @param login
     * @return
     */
    @Override
    public PostaddressDto qryPostAddress(Login login) {
        //查出顾客的购物卡号
        Userlist ul = ulDao.selectByPrimaryKey(login.getOpen_id());
        String gwkh = ul.getIdseq();//客人的购物卡号
        logger.info("获取顾客地址前查出客人购物卡号"+gwkh);

        PostaddressDto paDto;
        try {
            paDto = paDao.selectByPrimaryKey(gwkh);
            if (paDto != null) {
                logger.info("取到顾客"+ul.getName()+"的地址信息"+paDto.getRec_provincename()+
                        paDto.getRec_cityname()+paDto.getRec_areaname()+paDto.getRec_townname()
                        +paDto.getRec_detailaddress());
                paDto.setOpen_id(login.getOpen_id());
            }else {
                logger.error(gwkh+"获取到的对象值为空");
                throw new CustPostNotFoundException(errCode19,errMsg19);
            }
        } catch (Exception e) {
            logger.error(gwkh+"无可用地址");
            return new PostaddressDto();
        }
        return paDto;
    }

    /**
     * 新增邮寄地址
     * @param ipdDto
     * @return
     */
    @Override
    public int insertPostAddress(PostaddressDto ipdDto) {
        //查出顾客的购物卡号
        Userlist ul;
        try {
            ul = ulDao.selectByPrimaryKey(ipdDto.getOpen_id());
        } catch (Exception e) {
            logger.error(ipdDto.getGwkh()+"未注册");
            throw new CustPostNotFoundException(errCode7,errMsg7);
        }
        String gwkh = ul.getIdseq();
        ipdDto.setGwkh(ul.getIdseq());
        String recname = ul.getName();//EOP用户表中的顾客名称
        String crname = ipdDto.getRec_name();//接口传入的顾客名称
        logger.info(gwkh+"EOP用户表中的顾客名称"+recname);
        logger.info(gwkh+"接口传入的收件人名称"+crname);
        logger.info("新增顾客地址前查出客人购物卡号"+ul.getIdseq());
        int result;

        if ("海南省".equals(ipdDto.getRec_provincename())) {
            logger.info(gwkh+"收件地址必须是岛外");
            throw new CustPostNotFoundException(errCode20,errMsg20);
        }
        if (!recname.equals(crname)) {
            logger.info(gwkh+"收件必须是顾客本人");
            throw new CustPostNotFoundException(errCode20,errMsg20_1);
        }
        try {
            result = paDao.insert(ipdDto);
        } catch (Exception e) {
            logger.error(recname+gwkh+"在地址管理表中已经存在");
            throw new CustPostNotFoundException(errCode24,recname+gwkh+errMsg24);
        }
        if (result > 0) {
            logger.info(gwkh+"顾客"+ul.getName()+"地址新增成功");
        }
        return result;
    }

    @Override
    public int updatePostAddress(PostaddressDto ipaDto) {
        //查出顾客的购物卡号
        Userlist ul = ulDao.selectByPrimaryKey(ipaDto.getOpen_id());
        String gwkh = ul.getIdseq();
        ipaDto.setGwkh(gwkh);
        logger.info("新增顾客地址前查出客人购物卡号"+gwkh);
        int result;
        String recname = ul.getName();//EOP用户表中的顾客名称
        String crname = ipaDto.getRec_name();//接口传入的顾客名称
        if ("海南省".equals(ipaDto.getRec_provincename())) {
            logger.info("收件地址必须是岛外");
            throw new CustPostNotFoundException(errCode20,errMsg20);
        }
        if (!recname.equals(crname)) {
            logger.info("收件必须是顾客本人");
            throw new CustPostNotFoundException(errCode20,errMsg20_1);
        }
        try {
            result = paDao.updateByPrimaryKey(ipaDto);
            if (result > 0) {
                logger.info("顾客"+ul.getName()+"地址更新成功");
            }
        } catch (Exception e) {
            logger.error(gwkh+"邮寄地址管理表更新异常");
            throw new CustPostNotFoundException(errCode,errMsg);
        }
        return 0;
    }
}
