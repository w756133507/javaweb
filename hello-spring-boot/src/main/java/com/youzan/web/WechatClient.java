package com.youzan.web;

import com.youzan.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 李佳奇
 */
@Resource
public class WechatClient {
    private static final Logger logger = LoggerFactory.getLogger(WechatClient.class);
    @Autowired
    private RedisUtils redisUtils;
    private final String baseUrl;
    private final String appID;
    private final String secret;
    private final String grantType;

    public WechatClient(String baseUrl, String appID, String secret, String grantType) {
        this.baseUrl = baseUrl + "?grant_type=" + grantType + "&appid=" + appID + "&secret=" + secret;;
        this.appID = appID;
        this.secret = secret;
        this.grantType = grantType;
    }


    /**
     * 调用wechat API
     *
     * @param <Response> 返回值类型
     * @return Response 返回报文的泛型
     */
    public <Response> Response doGet(Class<Response> target)throws  Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new WechatResponseErrorHandler());
        if (logger.isDebugEnabled()) {
            logger.debug("Request info:{}", this.baseUrl);
        }
        try {
            return restTemplate.getForObject(this.baseUrl, target);
        } catch (RestClientException e) {
            logger.error("Call wechat api failed.", e);
            throw new Exception("Call wechat api failed.", e);
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAppID() {
        return appID;
    }

    public String getSecret() {
        return secret;
    }

    public String getGrantType() {
        return grantType;
    }


    public static void main(String[] args)throws  Exception  {
        WechatClient wc = new WechatClient("https://api.weixin.qq.com/cgi-bin/token",
                "wx095ae673e23d351e","ded6dd34293cce125fbd1f479ab268f7","client_credential");
        final WechatTokenResponseDto wechatTokenResponseDto = wc.doGet(WechatTokenResponseDto.class);
        System.out.println(wechatTokenResponseDto.getAccess_token());
    }
}
