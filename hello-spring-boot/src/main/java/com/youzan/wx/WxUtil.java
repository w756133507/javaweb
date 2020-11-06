package com.youzan.wx;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
/**
 *@Description TODO
 *@Author 王泽辉
 *@Date 2020/9/4 9:47
 */
public class WxUtil {
    /***** @description: 根据Code获取微信授权及openId
    　　* @author 王泽辉
    　　* @date 2020/9/4 9:47
    　　*/
    private static String getAccessTokenAccCode(String code) {
        RestTemplate restTemplate = new RestTemplate();
        final String appId = "wxcb16652466068eee";
        final String secret = "e6e50e9b6f7d07358d3b9db504e222b1";
        final String authorizationCode = "authorization_code";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appId}&secret={secret}&code={code}&grant_type={authorizationCode}";
        Map<String,String> params = new HashMap<String,String>();
        params.put("appId", appId);
        params.put("secret", secret);
        params.put("code",code);
        params.put("authorizationCode", authorizationCode);

        String resultMapStr = restTemplate.getForObject(url, String.class, params);
        HashMap<String, String> hashMap = JSON.parseObject(resultMapStr, HashMap.class);
         //微信服务器返回成功与失败格式如下:
        // {"session_key":"RQTCSXszF\/GVhlmHRICQEw==","openid":"oazGY5NjEPE7i-Za-Q6OpoByt5RI"}
        // {"errcode":40029,"errmsg":"invalid code, hints: [ req_id: 5fbdJRNre-l ]"}
        final String openid = hashMap.get("openid");
        if (StringUtils.isEmpty(openid)) {
            System.out.println("openid为空");
            //throw new NstException("对不起，openid为空");
        }
        return openid;
    }

}
