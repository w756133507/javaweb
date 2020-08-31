package com.youzan;  /**
 * @title: YouZanTest
 * @projectName Test
 * @description: TODO
 * @author PC
 * @date 2020/8/3013:53
 */

import com.google.common.collect.Maps;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.HttpConfig;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;

import com.youzan.cloud.open.sdk.core.client.core.YouZanClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;
import com.youzan.cloud.open.sdk.core.client.auth.Token;
import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanItemGet;
import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanScrmCardCreate;
import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanScrmCustomerCreate;
import com.youzan.cloud.open.sdk.gen.v3_0_0.model.*;
import okhttp3.OkHttpClient;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class YouZanTest {
    public static void main(String[] args) {

         //getToken(new DefaultYZClient());
        createRember();

        //免鉴权API调用
        //Direct direct = new Direct();
        //YouzanItemGetResult invoke = yzClient.invoke(kdtItemGet, direct, YouzanItemGetResult.class);

        //签名方式API调用
        //Sign sign = Sign.RSA2Builder()
        //    .identify("youzan_pay")
        //    .publicKeyId("10000000000004600")
        //    .clientPrivateKey("clientPrivateKey")
        //    .build();
        //YouzanItemGetResult invoke = yzClient.invoke(kdtItemGet, sign, YouzanItemGetResult.class);
    }


    /**
     *@Description 获取token
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void   createRember() {
     try {
         //初始化客户端
        YouZanClient yzClient = new DefaultYZClient();
        //获取token
         Token token = new Token(getToken(yzClient).getAccessToken());
         //设置参数
         YouzanScrmCustomerCreate youzanScrmCustomerCreate = new YouzanScrmCustomerCreate();
         YouzanScrmCustomerCreateParams youzanScrmCustomerCreateParams = new YouzanScrmCustomerCreateParams();



         YouzanScrmCustomerCreateParams.YouzanScrmCustomerCreateParamsCustomercreate customerCreateParamsCustomercreate = new YouzanScrmCustomerCreateParams.YouzanScrmCustomerCreateParamsCustomercreate();
         customerCreateParamsCustomercreate.setBirthday("1990-09-09");
         Short girl=1;
         customerCreateParamsCustomercreate.setGender(girl);
         customerCreateParamsCustomercreate.setName("蒋依依");


         YouzanScrmCustomerCreateParams.YouzanScrmCustomerCreateParamsContactaddress address=new YouzanScrmCustomerCreateParams.YouzanScrmCustomerCreateParamsContactaddress();
         address.setAddress("杭州市西湖区黄龙时代");


         customerCreateParamsCustomercreate.setContactAddress(address);
         youzanScrmCustomerCreateParams.setCustomerCreate(customerCreateParamsCustomercreate);
         youzanScrmCustomerCreateParams.setMobile("13599683456");



         YouzanScrmCardCreateResult result = yzClient.invoke(youzanScrmCustomerCreate, token, YouzanScrmCardCreateResult.class);
        }catch (SDKException e){
            e.printStackTrace();
        }
     }
    /**
     *@Description 获取token
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static OAuthToken  getToken(YouZanClient yzClient){
        try {
            //DefaultYZClient yzClient = new DefaultYZClient();
            TokenParameter tokenParameter = TokenParameter.self()
                    .clientId("4627cb89366371c68b")
                    .clientSecret("170c9d30cf93097eba1b4743829cd3c9")
                    .grantId("90909493")
                    .refresh(false)
                    .build();
            OAuthToken oAuthToken = yzClient.getOAuthToken(tokenParameter);
            System.out.println("获取token"+ oAuthToken);
            return oAuthToken;
        }catch (SDKException e){
            e.printStackTrace();
        }
        return null;
    }

}
