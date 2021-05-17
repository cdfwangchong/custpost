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

public class AuthUtil {
	
	static Logger logger = Logger.getLogger(AuthUtil.class);
	
	public final static String APPID = "wxda0f17566f1991b3";//测试
    public final static String APPSECRET ="4c626d03492593efaac6a845689a1dad";//测试
//	public final static String APPID = "wx9b6a1621e8902c87";//正式
//    public final static String APPSECRET ="21d3260f71a2d4ed331dc166898d5b13";//正式

    public static JSONObject doGetJson(String CODE_ID) {
    	
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID
	            + "&secret=" + APPSECRET
	            + "&code=" + CODE_ID
	            + "&grant_type=authorization_code";
    	
    	logger.info(CODE_ID);
    	logger.info(url);
        JSONObject jsonObject = null;
        //首先初始化HttpClient对象
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //通过get方式进行提交
        HttpGet httpGet = new HttpGet(url);
        //通过HTTPclient的execute方法进行发送请求
        HttpResponse response;
		try {
			response = client.execute(httpGet);
			 //从response里面拿自己想要的结果
	        HttpEntity entity = response.getEntity();
	        if(entity != null){
	            String result = EntityUtils.toString(entity,"UTF-8");
//	            jsonObject = JSONObject.fromObject(result);
	            jsonObject = JSONObject.parseObject(result);
	            
//	          //从返回的JSON数据中取出access_token和openid，拉取用户信息时用
//				if (jsonObject.containsKey("access_token")) {
//					String token =  jsonObject.getString("access_token");
//			        String openid = jsonObject.getString("openid");
//			        
//			        logger.info("token:"+token+";"+"openid:"+openid);
//				}else {
//					String errcode =  jsonObject.getString("errcode");
//			        String errmsg = jsonObject.getString("errmsg");
//			        
//			        logger.info("errcode:"+errcode+";"+"errmsg:"+errmsg);
//				}
	        }
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
        //把链接释放掉
        httpGet.releaseConnection();
        return jsonObject;
    }
}
