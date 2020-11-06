package com.boot.youzan.youzan.dto;

/***** @description: 微信网页授权dto
 　　* @author 王泽辉
 　　* @date 2020/9/7 15:45
 　　*/
public class WechatAccessToken {
    private String access_token;
    private String openId;
    private String refresh_token;
    private String scope;
    private int expires_in;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
