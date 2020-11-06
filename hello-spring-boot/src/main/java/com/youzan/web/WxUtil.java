package com.youzan.web;

import com.alibaba.fastjson.JSON;
import com.youzan.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/***** @description: 微信获取Access_token
　　* @author 王泽辉
　　* @date 2020/9/3 10:59
　　*/
@Component
public class WxUtil {
    private static final Logger logger = LoggerFactory.getLogger(WxUtil.class);
    //获取网页授权信息接口
    private static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid={appId}&secret={secret}&code={code}&grant_type={authorizationCode}";
    //获取用户信息接口
    private static String GET_WX_USERINFO_URL="https://api.weixin.qq.com/sns/userinfo?access_token={accessToken}&openid={openId}&lang={lang}";

    private static String GET_WX_TICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={accessToken}&type=jsapi";
     //appId
    //private static final String appId = "wxcb16652466068eee";
     //private static final String secret = "e6e50e9b6f7d07358d3b9db504e222b1";
     //private static final String authorizationCode = "authorization_code";
     @Value("${wx.appId}")
     private   String appId;
     @Value("${wx.secret}")
     private   String secret;
     @Value("authorization_code")
     private   String authorizationCode;
     @Autowired
     private   RedisUtils redisUtils;

    /***** @description: 获取微信网页授权
    　　* @author 王泽辉
    　　* @date 2020/9/7 15:58
    　　*/
    public  WechatAccessToken getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        String url = ACCESS_TOKEN_URL;
        Map<String,String> params = new HashMap<>();
        params.put("appId", appId);
        params.put("secret", secret);
        params.put("code",code);
        params.put("authorizationCode", authorizationCode);

        String resultMapStr = restTemplate.getForObject(ACCESS_TOKEN_URL, String.class, params);
        WechatAccessToken accessToken = JSON.parseObject(resultMapStr, WechatAccessToken.class);
        // 微信服务器返回成功与失败格式如下:
        // {"session_key":"RQTCSXszF\/GVhlmHRICQEw==","openid":"oazGY5NjEPE7i-Za-Q6OpoByt5RI"}
       //  {"errcode":40029,"errmsg":"invalid code, hints: [ req_id: 5fbdJRNre-l ]"}
        final String openid = accessToken.getOpenId();
        if (StringUtils.isEmpty(openid)) {
            System.out.println("openid为空");
           //throw new NstException("对不起，openid为空");
        }
        return accessToken;
    }

    /***** @description: 获取微信用户信息
     　　* @author 王泽辉
     　　* @date 2020/9/7 15:58
     　　*/
    public  HashMap<String, String> getWxUserInfo(String code) {
        WechatAccessToken accessToken=this.getAccessToken(code);
        HashMap<String, String> hashMap=null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String,String> params = new HashMap<String,String>();
            params.put("accessToken", accessToken.getAccess_token());
            params.put("openId", accessToken.getOpenId());
            params.put("lang","zh_CN");

            String resultMapStr = restTemplate.getForObject(GET_WX_USERINFO_URL, String.class, params);
            resultMapStr = new String(resultMapStr.getBytes("ISO-8859-1"), "UTF-8");
            hashMap = JSON.parseObject(resultMapStr, HashMap.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return hashMap;
    }

    /***** @description: 获取ticket
     　　* @author 王泽辉
     　　* @date 2020/9/7 15:58
     　　*/
    public  String getJsapiTicket(String accessToken,Boolean flag) {
        HashMap<String, String> hashMap=null;
        String  ticket=redisUtils.get("CRM_TICKET");
        try {
           if(ticket==null||"".equals(ticket)){
               RestTemplate restTemplate = new RestTemplate();
               Map<String,String> params = new HashMap<String,String>();
               params.put("accessToken", accessToken);

               String resultMapStr = restTemplate.getForObject(GET_WX_TICKET_URL, String.class, params);
               resultMapStr = new String(resultMapStr.getBytes("ISO-8859-1"), "UTF-8");
               hashMap = JSON.parseObject(resultMapStr, HashMap.class);
               ticket=hashMap.get("ticket");
               redisUtils.set("CRM_TICKET",ticket);//存redis中
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ticket;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public static void main(String[] args) {
//        WechatClient wc = new WechatClient("https://api.weixin.qq.com/cgi-bin/token",
//                "wx672a8edff55629f4","b238caa335b6bbd986ac8a5327149784","client_credential");
//        final WechatTokenResponseDto wechatTokenResponseDto = wc.doGet(WechatTokenResponseDto.class);
//        System.out.println(wechatTokenResponseDto.getAccess_token());

        //https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
//        final String appId = "wxcb16652466068eee";
//        final String secret = "e6e50e9b6f7d07358d3b9db504e222b1";
//        final String authorizationCode = "authorization_code";
//        String code="031mOb000a05fK1e9v0009YIEn2mOb0L";
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
//        WechatClient wc = new WechatClient(url, appId,secret,authorizationCode);
//        final WechatTokenResponseDto wechatTokenResponseDto = wc.doGet(WechatTokenResponseDto.class);
//        System.out.println(wechatTokenResponseDto.getAccess_token());

        //System.out.println(getWxUserInfo("001QDpGa118Myz0FjmFa1KgaqR0QDpGd"));

    }
}
