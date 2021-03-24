package com.ctg.custpost.service.impl;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.ctg.custpost.dao.CheckCanclePostDao;
import com.ctg.custpost.dao.CustaddrlistDao;
import com.ctg.custpost.dao.UserlistDao;
import com.ctg.custpost.pojo.dto.InsertCustAddrAndListDto;
import com.ctg.custpost.pojo.dto.InsertCustAddrDto;
import com.ctg.custpost.pojo.dto.Userlist;
import com.ctg.custpost.pojo.until.BillEntity;
import com.ctg.custpost.service.CustAddrListService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ctg.custpost.pojo.until.Constant.*;

@Service
public class CustAddrListServiceImpl implements CustAddrListService {
    @Autowired
    CustaddrlistDao clDao;

    @Autowired
    UserlistDao ulDao;

    @Autowired
    CheckCanclePostDao ccpDao;

    Logger logger = Logger.getLogger(CustAddrListServiceImpl.class);

    /**
     * 商品邮寄接口
     * @param ica
     * @return
     */
    @Override
    public boolean insertCustAddrList(InsertCustAddrAndListDto ica) {
        //当取消邮寄申请时，判断是否符合条件取消
        Map param = new HashMap<String,Integer>();
        if ("2".equals(ica.getType())) {
            param.put("seq_no",ica.getSeq_no());
            String ret_flag;
            try {
                ccpDao.isCanclePost(param);
                ret_flag = (String) param.get("ret_flag");
            } catch (Exception e) {
                logger.error("取消邮寄申请时，判断是否符合条件取消存储过程返回异常");
                throw new CustPostNotFoundException(errCode3,errMsg3);
            }

            if ("2001".equals(ret_flag)) {
                logger.info("离航班起飞时间不足48小时，不能取消");
                throw new CustPostNotFoundException(errCode12,errMsg12);
            }
            if ("2002".equals(ret_flag)) {
                logger.info("该邮寄申请单中包含状态为已提货的提货单，不能取消");
                throw new CustPostNotFoundException(errCode13,errMsg13);
            }
        }
        //查出顾客的购物卡号
        Userlist ul = ulDao.selectByPrimaryKey(ica.getOpen_id());
        String gwkh = ul.getIdseq();//客人的购物卡号
        String telphno = ul.getName();//客人姓名
        if (!telphno.equals(ica.getRec_name())) {
            logger.info("收件人必须是顾客本人");
            throw new CustPostNotFoundException(errCode10,errMsg10);
        }
        if ("海南省".equals(ica.getRec_provincename())) {
            logger.info("收件地址必须是岛外");
            throw new CustPostNotFoundException(errCode20,errMsg20);
        }
        List<BillEntity> PIlist = ica.getOrderList();
        Map<String,String> Markmap = new HashMap<String,String>();
        Markmap.put("6868",null);
        Markmap.put("6921",null);
        Markmap.put("6922",null);
        Markmap.put("6127",null);
        Markmap.put("6132",null);
        Markmap.put("7016",null);
        //记录包裹数
        Map<String, Integer> pcgCntMap = new HashMap<String,Integer>();
        pcgCntMap.put("6868",0);
        pcgCntMap.put("6921",0);
        pcgCntMap.put("6922",0);
        pcgCntMap.put("6127",0);
        pcgCntMap.put("6132",0);
        pcgCntMap.put("7016",0);

        int pcgCntsy= 0;
        int pcgCntba= 0;
        int pcgCnthk= 0;
        int pcgCntml= 0;
        int pcgCntsyjc= 0;
        int pcgCntmljc= 0;
        List<InsertCustAddrDto> icadList = new ArrayList<InsertCustAddrDto>();
        //拼接各门店的提货单号
        if (PIlist != null) {
            for (int i = 0; i < PIlist.size(); i++) {
                BillEntity pi = PIlist.get(i);
                if ("6868".equals(pi.getMarket()) || "6874".equals(pi.getMarket())) {
                    String billno = Markmap.get("6868");
                    if (billno == null) {
                        Markmap.put("6868",pi.getShxsdno());
                    }else {
                        billno = pi.getShxsdno()+"|"+billno;
                        Markmap.put("6868",billno);
                    }
                    pcgCntsy++;
                    pcgCntMap.put("6868",pcgCntsy);

                } else if("6921".equals(pi.getMarket())) {
                    String billno = Markmap.get("6921");
                    if (billno == null) {
                        Markmap.put("6921",pi.getShxsdno());
                    }else {
                        billno = pi.getShxsdno()+"|"+billno;
                        Markmap.put("6921",billno);
                    }
                    pcgCntba++;
                    pcgCntMap.put("6921",pcgCntba);

                }else if ("6922".equals(pi.getMarket())) {
                    String billno = Markmap.get("6922");
                    if (billno == null) {
                        Markmap.put("6922",pi.getShxsdno());
                    }else {
                        billno = pi.getShxsdno()+"|"+billno;
                        Markmap.put("6922",billno);
                    }
                    pcgCnthk++;
                    pcgCntMap.put("6922",pcgCnthk);

                }else if ("6127".equals(pi.getMarket())){
                    String billno = Markmap.get("6127");
                    if (billno == null) {
                        Markmap.put("6127",pi.getShxsdno());
                    }else {
                        billno = pi.getShxsdno()+"|"+billno;
                        Markmap.put("6127",billno);
                    }
                    pcgCntml++;
                    pcgCntMap.put("6127",pcgCntml);

                }else if ("6132".equals(pi.getMarket())) {
                    String billno = Markmap.get("6132");
                    if (billno == null) {
                        Markmap.put("6132",pi.getShxsdno());
                    }else {
                        billno = pi.getShxsdno()+"|"+billno;
                        Markmap.put("6132",billno);
                    }
                    pcgCntmljc++;
                    pcgCntMap.put("6132",pcgCntmljc);

                }else if ("7016".equals(pi.getMarket())) {
                    String billno = Markmap.get("7016");
                    if (billno == null) {
                        Markmap.put("7016",pi.getShxsdno());
                    }else {
                        billno = pi.getShxsdno()+"|"+billno;
                        Markmap.put("7016",billno);
                    }
                    pcgCntsyjc++;
                    pcgCntMap.put("7016",pcgCntsyjc);

                }else {
                    throw new CustPostNotFoundException(errCode2,errMsg2);
                }
            }
        }
        try {
            //将有值的Markmap存入List
            for (Map.Entry<String,String> entry : Markmap.entrySet()) {
                if (entry.getValue() != null) {
                    InsertCustAddrDto icaDto = new InsertCustAddrDto();
                    icaDto.setRec_name(ica.getRec_name());
                    icaDto.setRec_phoneno(ica.getRec_phoneno());
                    icaDto.setRec_postcode(ica.getRec_postcode());
                    icaDto.setRec_provincename("");
                    icaDto.setRec_cityname("");
                    icaDto.setRec_areaname("");
                    icaDto.setRec_townname("");
                    icaDto.setRec_detailaddress("");
                    icaDto.setType(ica.getType());
                    icaDto.setMarket(entry.getKey());
                    icaDto.setRec_xsdno(entry.getValue());
                    icaDto.setRec_pkgcnt(pcgCntMap.get(entry.getKey()));
                    icaDto.setGwkh(gwkh);
                    icaDto.setFlag("0");
                    icaDto.setSeqno_c(ica.getSeq_no());

                    icadList.add(icaDto);
                    //将客人的邮寄信息写入日志
                    String address = ica.getRec_provincename()+ica.getRec_cityname()+ica.getRec_areaname()+ica.getRec_townname()+ica.getRec_detailaddress();
                    logger.info("顾客："+ica.getRec_name()+"电话："+ica.getRec_phoneno()+"地址："+address+"邮寄的提货单"+entry.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("邮寄信息写入异常");
            throw new CustPostNotFoundException(errCode3,errMsg3);
        }
            int ret = clDao.insert(icadList);
            if (ret != icadList.size()) {
                logger.error("List中的数据没有正确写入顾客地址列表");
                throw new CustPostNotFoundException(errCode4,errMsg4);
            }
        return true;
    }
}
