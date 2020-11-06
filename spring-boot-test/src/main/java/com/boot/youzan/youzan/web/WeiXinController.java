package com.boot.youzan.youzan.web;

import com.alibaba.fastjson.JSONObject;
import com.boot.youzan.youzan.util.WxUtil;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    @Source
    private RestTemplate restTemplate;

    @GetMapping("/getUserInfo")
    public Object getUserInfo(String code) {

        return wxUtil.getWxUserInfo(code);
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
}
