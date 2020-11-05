package com.youzan.web;
/**
*@Description TODO
*@Author 王泽辉
*@Date 2020/10/29 14:28
*/

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class HBYAppUtils {

    String filePath = "data_file_";
    String MainType = "live";
    String sub_type="share";
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "PYr6HBlezAeQglU5nJNig9k1HwcRFZRNhYAGHyVw";
    //这两个登录七牛 账号里面可以找到
    String SECRET_KEY = "6WBtlaFOBRbjCfPLlh5FfbHLStyIT-4gGmm8U6v6";
    //要上传的空间
    String bucketname = "hxtide";

    String domain_of_bucket="http://imageerp.hxtide.com/";


    //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）
    // 上传到七牛后保存的文件名
//    String key = "123.html";
    //上传文件的路径
//    String FilePath = "C:\\Users\\沐白\\Desktop\\肺炎疫情武汉加油.html";
    //本地要上传文件路径

    /*
　　　　普通上传
　　　　multipartFile : form表单传过来的文件,
　　　　key: 文件名
　　*/
    public  String  upload(MultipartFile file){
        // 密钥配置
        Auth auth = Auth.create(ACCESS_KEY,SECRET_KEY);
        //创建上传对象
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        String  fileId=UUID.randomUUID().toString();
        String result = "";
        String key = filePath + MainType + "_" + sub_type+"_"+fileId;
        try {
            //调用put方法上传
            Response response = uploadManager.put(file.getInputStream(),key,auth.uploadToken(bucketname),null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //打印返回的信息
            //result = key;
            System.out.println("statusCode : "+response.statusCode);
            System.out.println(putRet.key);
            result = domain_of_bucket + filePath+ MainType+"_" + sub_type+"_"+fileId;
        } catch (QiniuException e) {
            e.printStackTrace();
            // 请求失败时打印的异常的信息
            result = "no";
        } catch (IOException e){
            e.printStackTrace();
        }
        return  result;

    }
}
