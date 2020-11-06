package com.youzan.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.youzan.util.RedisUtils;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Description 有赞推送消息入口
 * @Author 王泽辉
 * @Date 2020/9/1 9:59
 */
@RestController
@RequestMapping(value = "/weixin")
public class WeiXinController {

    @Autowired
    private WxUtil wxUtil;


    @Autowired
    private RedisUtils redisUtils;
    @Value(value = "{wx.token}")
    private String token;
    @Source
    private RestTemplate restTemplate;

    /**
     * 微信公众号签名认证接口
     * @Title: test
     * @Description: TODO
     * @param: @param signature
     * @param: @param timestamp
     * @param: @param nonce
     * @param: @param echostr
     * @param: @return
     * @return: String
     * @throws
     */
    @RequestMapping("/wx")
    public String test(String signature,String timestamp,String nonce,String echostr) {

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && WeixinSignatureUtil.checkSignature(token,signature, timestamp, nonce)) {
            return echostr;
        }

        return null;
    }

    @GetMapping("/getUserInfo")
    public Object getUserInfo(String code) {
        return wxUtil.getWxUserInfo(code);
    }
    //全局token
    @GetMapping("/refreshToken")
    public Object  refreshToken()throws  Exception {
        return getCommonToken(true);
    }
    //全局token
    @GetMapping("/refreshJsapiTicket")
    public Object  refreshJsapiTicket()throws  Exception {
        return wxUtil.getJsapiTicket(getCommonToken(false),true);
    }
   //全局token
    @GetMapping("/getToken")
    public Object getToken()throws  Exception {
        return getCommonToken(false);
    }
    //获取用户授权token
    @GetMapping("/getAccessToken")
    public Object getAccessToken(String code)throws  Exception {
          return wxUtil.getAccessToken(code);
    }
    //获取用户授权token
    @GetMapping("/getJsapiTicket")
    public Object getJsapiTicket()throws  Exception {
        return wxUtil.getJsapiTicket(getCommonToken(false),false);
    }
    //获取签名
    @GetMapping("/getSignature")
    public Object getSignature(String url)throws  Exception {
        //url=java.net.URLDecoder.decode(url, "UTF-8");
        String accessToken=getCommonToken(false);
        //获取临时票据
        String ticket=wxUtil.getJsapiTicket(getCommonToken(false),false);
        //生成签名
        String noncestr = generateStr(10);//随机字符串
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
        //4获取url
        //5将参数排序并拼接字符串
        String str = "jsapi_ticket="+ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
       //6、将字符串进行sha1加密
        String signature = WeixinSignatureUtil.sha1(str);
        Map<String,String> map=new HashMap();
        map.put("timestamp",timestamp);
        map.put("accessToken",accessToken);
        map.put("ticket",ticket);
        map.put("noncestr",noncestr);
        map.put("signature",signature);
        return map;
    }
    private JSONObject buildSuccessResponse() {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", "success");
        return res;
    }

    private JSONObject buildFailedResponse() {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", "failed");
        return res;
    }

    private void doSomething(String entity) {

    }
    //wxcb16652466068eee
    //e6e50e9b6f7d07358d3b9db504e222b1
    private String getCommonToken(Boolean flag) throws Exception{
        String access_token=redisUtils.get("CRM_ACCESSTOKEN");
         WechatTokenResponseDto wechatTokenResponseDto=null;
        if(access_token==null||flag==true){
            WechatClient wc = new WechatClient("https://api.weixin.qq.com/cgi-bin/token",
                    "wx095ae673e23d351e","ded6dd34293cce125fbd1f479ab268f7","client_credential");
            wechatTokenResponseDto = wc.doGet(WechatTokenResponseDto.class);
            access_token=wechatTokenResponseDto.getAccess_token();
            redisUtils.set("CRM_ACCESSTOKEN", access_token);
        }
        return access_token;
    }

    public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 产生len长度的随机字符串
     * @param len
     * @return
     */
    public static String generateStr(int len){
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i <len ; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return  sb.toString();
    }
}
