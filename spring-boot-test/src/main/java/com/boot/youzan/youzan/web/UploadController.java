package com.boot.youzan.youzan.web;

import com.boot.youzan.youzan.util.HBYAppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *@Description 有赞推送消息入口
 *@Author 王泽辉
 *@Date 2020/9/1 9:59
 */
@RestController
@RequestMapping(value = "/")
public class UploadController {
    //private static final String CLIENT_ID="edb04b22672a9cb65f"; //应用的 client_id
    //private static final String CLIENT_SECRET="bc260c07482076c5ef616f78868cfc78";//应用的 client_secret
    @Autowired
    private HBYAppUtils hBYAppUtils;
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file)  {
        // 获取文件的名称
        return hBYAppUtils.upload(file);
    }


}
