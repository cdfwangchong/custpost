package com.ctg.custpost.pojo.until;

import java.util.HashMap;
import java.util.Map;

public class LoginErrCode {
    private static Map<String,String> msg = new HashMap<>();

    static {
        msg.put("0","无购物记录不能注册");
        msg.put("1","成功");
        msg.put("2","该手机号已经注册");
        msg.put("3","和店内预留手机号不一致");
        msg.put("2002","未注册，无记录");

    }

    public static String getMsg(String key) {
        if (msg.containsKey(key)){
            return msg.get(key);
        }else {
            return "未知错误码";
        }
    }
}
