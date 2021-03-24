package com.ctg.custpost.pojo.until;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * 获取微信头像
 */
public class WxtxUtil {
    static Logger logger = Logger.getLogger(WxtxUtil.class);

    public static String getWxtx(String openId,String token) {
        {
            String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token
                    + "&openid=" + openId
                    + "&lang=zh_CN";

            logger.info(token);
            logger.info(url);
            JSONObject jsonObject = null;
            //首先初始化HttpClient对象
            CloseableHttpClient client = HttpClientBuilder.create().build();
            //通过get方式进行提交
            HttpGet httpGet = new HttpGet(url);
            //通过HTTPclient的execute方法进行发送请求
            HttpResponse response;
            String result=null;
            try {
                response = client.execute(httpGet);
                //从response里面拿自己想要的结果
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //把链接释放掉
            httpGet.releaseConnection();
            return result;

        }
    }
}
