package com.ctg.custpost.controller;

import cn.ctg.exceptionHandle.CustPostNotFoundException;
import com.alibaba.fastjson.JSONObject;
import com.ctg.custpost.pojo.dto.Postvercode;
import com.ctg.custpost.pojo.dto.WechatCode;
import com.ctg.custpost.pojo.until.*;
import com.ctg.custpost.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ctg.custpost.pojo.until.Constant.*;

/*
 * project name :自助邮寄
 * for:用户注册
 * author：wangc
 * time：2020-10-10
 * */

@Controller
@RequestMapping("/cdfg")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService registerservice=null;

    Logger logger = Logger.getLogger(UserController.class);

    /**
     * 注册接口
     * @param reguser
     * @return
     * @throws NullPointerException
     */
    @PostMapping("/getregister")
    @ResponseBody
    public Result<String> cdfgdeposit(@RequestBody RegUser reguser) throws NullPointerException {

        String id_type = reguser.getId_type();//证件类型
        String card_id = reguser.getCard_id();//证件号
        String tel_num = reguser.getTel_num();//手机号
        String open_id = reguser.getOpen_id();//密码

        logger.info("取到注册用户信息"+id_type+card_id+tel_num+open_id);

        Map<String,String> param=new HashMap<String,String>();
        param.put("id_type", id_type);
        param.put("card_id", card_id);
        param.put("tel_num", tel_num);
        param.put("open_id", open_id);

        try {
            registerservice.getCoupon(param);
        } catch (Exception e) {
            logger.error("获取注册用户信息存储过程的返回值异常");
            throw new CustPostNotFoundException(errCode,errMsg);
        }
        int code = Integer.parseInt(param.get("ret_flag")) ;
        //写入日志
        logger.info("ret_flag（返回标志）:"+ param.get("ret_flag"));
        if ("1".equals(param.get("ret_flag"))) {
            return new Result<String>(code, LoginErrCode.getMsg(param.get("ret_flag")), null);
        }else {
            throw new CustPostNotFoundException(code,LoginErrCode.getMsg(param.get("ret_flag")));
        }
    }

    /**
     * 登录接口
     * @param login
     * @return
     */
    @PostMapping("/getlogin")
    @ResponseBody
    public Result<Map> login(@RequestBody Login login) {

        String open_id = login.getOpen_id();//密码

        Map<String,String> retparam=new HashMap<String,String>();
        logger.info("取到登录用户信息"+open_id);

        Map<String,String> param=new HashMap<String,String>();
        param.put("open_id", open_id);

        String ret_flag;
        try {
            registerservice.login(param);
            //获得返回值
            ret_flag=param.get("ret_flag");
        } catch (Exception e) {
            logger.error("取到登录用户信息存储过程的返回值异常");
            throw new CustPostNotFoundException(errCode9,errMsg9);
        }

        if ("2002".equals(ret_flag)) {
            logger.error("未注册，无记录");
            throw new CustPostNotFoundException(errCode7,errMsg7);
        }else {
            retparam.put("card_id",param.get("card_id"));
            retparam.put("tel_num",param.get("tel_num"));
            retparam.put("user_name",param.get("user_name"));
            retparam.put("id_type",param.get("id_type"));
            }

        logger.info("获取登录信息返回标志："+ret_flag+"用户名："+param.get("user_name")+"身份证："+param.get("card_id")+"电话："+param.get("tel_num"));
        return new Result<Map>(sucCode,sucMsg,retparam);
    }

    /**
     * 接收验收码接口
     * @param vercode
     * @return
     */
    @PostMapping("/insertvercode")
    @ResponseBody
    public Result<String> cdfgdeposit(@RequestBody Vercode vercode) {

        int ret_flag;

        String tel_num = vercode.getTel_num();//取到电话代码
        String open_Id = vercode.getOpenId();
        String gwkh = vercode.getCardType()+vercode.getCardId();
        //生成一个6位数的随机数
        String code = String.valueOf((Math.random()*9+1)*100000);
        int idx = code.lastIndexOf(".");
        String ver_code = code.substring(0,idx);

        try{
            Postvercode pv = new Postvercode();
            pv.setGwkh(gwkh);
            pv.setOpenid(open_Id);
            pv.setTelnum(tel_num);
            pv.setVercode(ver_code);

            logger.info("获取到客人电话号码："+tel_num+" 验证码："+ver_code);
            ret_flag = registerservice.insert(pv);

            if (ret_flag == 0) {
                logger.info(tel_num+"验证码写入返回值异常");
            }
            //写入日志
            logger.info("ret_flag（返回标志）:"+ret_flag);
        } catch (Exception e) {
            logger.error("生成验证码后写入表异常");
            throw new CustPostNotFoundException(errCode11,errMsg11);
        }
        return new Result<String>(sucCode,sucMsg,"");
    }

    /**
     * @param code
     * @return
     */
    @PostMapping("/getvercode")
    @ResponseBody
    public Result<String> getVercode(@RequestBody Vercode code) {
        Postvercode pvcode;
        String tel_num = code.getTel_num();//取到电话代码
        String gwkh = code.getCardType()+code.getCardId();
        String userCode = code.getVerCode();

        Map param=new HashMap<String,String>();
        param.put("tel_num", tel_num);
        param.put("gwkh", gwkh);

        try {
            pvcode = registerservice.selectByPrimaryKey(param);
        } catch (Exception e) {
            logger.error("验证码获取存返回值异常");
            throw new CustPostNotFoundException(errCode17,errMsg17);
        }
        if (pvcode == null) {
            logger.error("该手机未获取验证码");
            throw new CustPostNotFoundException(errCode18,errMsg18);
        }
        String orlCode = pvcode.getVercode();
        Date fsTime = pvcode.getFstime();

        Calendar dateOne = Calendar.getInstance();
        Calendar dateTwo = Calendar.getInstance();

        dateOne.setTime(new Date());
        dateTwo.setTime(fsTime);

        long timeOne=dateOne.getTimeInMillis();
        long timeTwo=dateTwo.getTimeInMillis();

        long minute=(timeOne-timeTwo)/(1000*60);//转化minute
        if (minute > 5) {
            logger.info("验证码已过期");
            throw new CustPostNotFoundException(errCode15,errMsg15);
        }else if (userCode.equals(orlCode)) {
            return new Result<String>(sucCode,sucMsg,"");
        }else {
            logger.info("验证码错误");
            throw new CustPostNotFoundException(errCode16,errMsg16);
        }
    }

    /**
     * 获取Openid接口
     * @param code
     * @return
     */
//    @PostMapping("/getopenid")
    @RequestMapping(value={"/getopenid"}, method = RequestMethod.POST)
    @ResponseBody
    public Result<Map> wechatopenid(@RequestBody WechatCode code) {

        Map<String,String> retparam=new HashMap<String,String>();
        String vercode = code.getVer_code();

        logger.info("取到code"+vercode);
        String ret_flag;
        String card_id;
        String tel_num;
        String user_name;

        JSONObject jsonObject;
        jsonObject = AuthUtil.doGetJson(vercode);

        //从返回的JSON数据中取出access_token和openid，拉取用户信息时用
        if (jsonObject.containsKey("access_token")) {
            String token =  jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
            logger.info("token:"+token+";"+"openid:"+openid);
            String ret = WxtxUtil.getWxtx(openid,token);//取到客人头像
            //获取到客人信息
            Map<String,String> param=new HashMap<String,String>();
            param.put("open_id", openid);
            logger.info("获取到客人openid："+openid);

            try{
                registerservice.weChat(param);
            } catch (Exception e) {
                logger.error("获取openid存储过程的返回值异常");
                throw new CustPostNotFoundException(errCode8,errMsg8);
            }
            //获得返回值
            ret_flag=param.get("ret_flag");
            card_id=param.get("card_id");
            tel_num=param.get("tel_num");
            user_name=param.get("user_name");
            //写入日志
            logger.info("ret_result（返回结果）:"+ret_flag+card_id+tel_num+openid+user_name);
            retparam.put("access_token",token);
            retparam.put("open_id",openid);
//            retparam.put("tel_num",tel_num);
//            retparam.put("card_id",card_id);
//            retparam.put("user_name", user_name);
            retparam.put("userinfo",ret);
            }else {
                String errcode =  jsonObject.getString("errcode");
                String errmsg = jsonObject.getString("errmsg");

                logger.info("errcode:"+errcode+";"+"errmsg:"+errmsg);
                logger.info("ret_result（返回结果）:"+"errcode:"+errcode+"errmsg:"+errmsg);
                throw new CustPostNotFoundException(errCode8,errMsg8);
            }

        return new Result<Map>(sucCode,sucMsg,retparam);
    }
}
