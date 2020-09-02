package com.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *@Description 有赞推送消息入口
 *@Author 王泽辉
 *@Date 2020/9/1 9:59
 */
@RestController
@RequestMapping(value = "/youzan/push")
public class YouzanPushController {


    //private static final String CLIENT_ID="edb04b22672a9cb65f"; //应用的 client_id
    //private static final String CLIENT_SECRET="bc260c07482076c5ef616f78868cfc78";//应用的 client_secret
    @GetMapping("/receive")
    public Object receive(@RequestBody Map<String,Object> map){
        System.out.println("——————");
        return buildSuccessResponse();
    }
    private JSONObject buildSuccessResponse(){
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", "success");
        return res;
    }
    private JSONObject buildFailedResponse(){
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", "failed");
        return res;
    }
    private void doSomething(String entity) {

    }
}
