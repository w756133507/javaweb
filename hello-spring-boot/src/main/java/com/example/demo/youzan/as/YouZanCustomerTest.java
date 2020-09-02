package com.youzan;
/**
 * @projectName Test
 * @description: TODO
 * @author PC
 * @date 2020/8/3013:53
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.auth.Token;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.core.client.core.YouZanClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;
import com.youzan.cloud.open.sdk.gen.v3_0_0.api.*;
import com.youzan.cloud.open.sdk.gen.v3_0_0.model.*;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanScrmCustomerCardDeleteParams;

import java.util.ArrayList;
import java.util.List;


/***** @description: 会员卡信息
　　* @author 王泽辉
　　* @date 2020/8/31 12:02
　　*/
public class YouZanCustomerTest {
    public static void main(String[] args) {

         //getToken(new DefaultYZClient());
         //createRember();
         //delRember();
        //delRemberBySaler();
           getRemberList();
        // updateRember();
    }
    /**
     *@Description 获取会员信息
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void   getRemberList() {
        try {
           //YouZanClient 建议全局唯一,使用 spring 容器管理
            DefaultYZClient yzClient = new DefaultYZClient();
            Token token = new Token( getToken(yzClient).getAccessToken());
            System.out.println(token.getAccessToken());

            YouzanScrmCardList youzanScrmCardList = new YouzanScrmCardList();
            //创建参数对象,并设置参数
            YouzanScrmCardListParams youzanScrmCardListParams = new YouzanScrmCardListParams();
            youzanScrmCardListParams.setPage(1);
            youzanScrmCardList.setAPIParams(youzanScrmCardListParams);

            YouzanScrmCardListResult result = yzClient.invoke(youzanScrmCardList, token, YouzanScrmCardListResult.class);

            System.out.println(JSON.toJSONString(result));
        }catch (SDKException e){
            e.printStackTrace();
        }
    }
    /**
     *@Description 修改会员信息
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void   updateRember() {
        try {
            DefaultYZClient yzClient = new DefaultYZClient();
            Token token = new Token( getToken(yzClient).getAccessToken());
            System.out.println(token.getAccessToken());
            YouzanScrmCardUpdate youzanScrmCardUpdate = new YouzanScrmCardUpdate();

            //创建参数对象,并设置参数
            YouzanScrmCardUpdateParams youzanScrmCardUpdateParams = new YouzanScrmCardUpdateParams();
            YouzanScrmCardUpdateParams.YouzanScrmCardUpdateParamsCardupdate a=new YouzanScrmCardUpdateParams.YouzanScrmCardUpdateParamsCardupdate();
            a.setCardAlias("Y3ey759c03z479");
            a.setServicePhone("17702067821");

            a.setActivateMode(0);
            a.setColorCode("#55bd47");
            a.setIsAllowShare(true);
            a.setTermDays(365);
            a.setTermType(3);
            a.setSyncWeixinMode(0);
            a.setDescription("商家创建会员卡");
            a.setName("member1");
            youzanScrmCardUpdateParams.setCardUpdate(a);

            List<YouzanScrmCardUpdateParams.YouzanScrmCardUpdateParamsRights> rightsList=new ArrayList<>();
            YouzanScrmCardUpdateParams.YouzanScrmCardUpdateParamsRights rights=new YouzanScrmCardUpdateParams.YouzanScrmCardUpdateParamsRights();
            rights.setIsAvailable(true);
            rights.setType(2);
            rights.setDiscount(80);
            rights.setName("卡模板0");
            a.setRights(rightsList);

            youzanScrmCardUpdate.setAPIParams(youzanScrmCardUpdateParams);
            YouzanScrmCardUpdateResult result = yzClient.invoke(youzanScrmCardUpdate, token, YouzanScrmCardUpdateResult.class);
            System.out.println(JSON.toJSONString(result));
            System.out.println(result.getMessage());
        }catch (SDKException e){
            e.printStackTrace();
        }
    }

    /**
     *@Description 创建会员
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void   createRember() {
        try {
            DefaultYZClient yzClient = new DefaultYZClient();
            Token token = new Token( getToken(yzClient).getAccessToken());
            System.out.println(token.getAccessToken());
            YouzanScrmCardCreate youzanScrmCardCreate = new YouzanScrmCardCreate();

            //创建参数对象,并设置参数
            YouzanScrmCardCreateParams youzanScrmCardCreateParams = new YouzanScrmCardCreateParams();
            YouzanScrmCardCreateParams.YouzanScrmCardCreateParamsCardcreate a=new YouzanScrmCardCreateParams.YouzanScrmCardCreateParamsCardcreate();
            a.setActivateMode(0);
            a.setCardType(1);
            a.setColorCode("#55bd47");
            a.setIsAllowShare(true);
            a.setTermDays(365);
            a.setTermType(3);
            a.setSyncWeixinMode(0);
            a.setDescription("商家创建会员卡");
            a.setName("member0");
            youzanScrmCardCreateParams.setCardCreate(a);

            List<YouzanScrmCardCreateParams.YouzanScrmCardCreateParamsRights> rightsList=new ArrayList<>();
            YouzanScrmCardCreateParams.YouzanScrmCardCreateParamsRights rights=new YouzanScrmCardCreateParams.YouzanScrmCardCreateParamsRights();
            rights.setIsAvailable(true);
            rights.setType(1);
            rights.setName("卡模板0");

            a.setRights(rightsList);

            youzanScrmCardCreate.setAPIParams(youzanScrmCardCreateParams);
            YouzanScrmCardCreateResult result = yzClient.invoke(youzanScrmCardCreate, token, YouzanScrmCardCreateResult.class);
            System.out.println(JSON.toJSONString(result.getData()));
            System.out.println(result.getMessage());
        }catch (SDKException e){
            e.printStackTrace();
        }
    }
    /**
     *@Description 多商品下单 API 接口使用指南
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void   createOrder() {
     try {
         DefaultYZClient yzClient = new DefaultYZClient();
         Token token = new Token( getToken(yzClient).getAccessToken());
         System.out.println(token.getAccessToken());
         // 传入参数
         JSONObject jsonObject = new JSONObject();
         jsonObject.put("goods_id",609186196);
         jsonObject.put("num",1);

         YouzanTradeBillGoodsUrlGet youzanTradeBillGoodsUrlGet = new YouzanTradeBillGoodsUrlGet();
         //创建参数对象,并设置参数
         YouzanTradeBillGoodsUrlGetParams youzanTradeBillGoodsUrlGetParams = new YouzanTradeBillGoodsUrlGetParams();
         youzanTradeBillGoodsUrlGetParams.setKdtId(90909493L);
         youzanTradeBillGoodsUrlGetParams.setOrderFrom("cart");
         youzanTradeBillGoodsUrlGetParams.setOrderType(0L);
         System.out.println(jsonObject.toJSONString());

         JSONArray jsonArray = new JSONArray();
         jsonArray.add(jsonObject);

         youzanTradeBillGoodsUrlGetParams.setItemList(jsonArray.toJSONString());
         youzanTradeBillGoodsUrlGet.setAPIParams(youzanTradeBillGoodsUrlGetParams);
         YouzanTradeBillGoodsUrlGetResult result = yzClient.invoke(youzanTradeBillGoodsUrlGet, token, YouzanTradeBillGoodsUrlGetResult.class);
         JSONObject jsonObject1  = JSON.parseObject(JSON.toJSONString(result.getResponse().getIsSuccess()));
         System.out.println(jsonObject1);
        }catch (SDKException e){
            e.printStackTrace();
        }
     }
    /**
     *@Description 删除用户的会员卡
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void  delRember() {
        try {
            //YouZanClient 建议全局唯一,使用 spring 容器管理
            DefaultYZClient yzClient = new DefaultYZClient();
            Token token = new Token(getToken(yzClient).getAccessToken());
            System.out.println(token.getAccessToken());

            YouzanScrmCustomerCardDelete youzanScrmCustomerCardDelete = new YouzanScrmCustomerCardDelete();
            //创建参数对象,并设置参数
            YouzanScrmCustomerCardDeleteParams a = new YouzanScrmCustomerCardDeleteParams();

            YouzanScrmCustomerCardDeleteParams.YouzanScrmCustomerCardDeleteParamsParams params=new YouzanScrmCustomerCardDeleteParams.YouzanScrmCustomerCardDeleteParamsParams();
            params.setKdtId(90909493L);
            a.setParams(params);

            YouzanScrmCustomerCardDeleteParams.YouzanScrmCustomerCardDeleteParamsUser user=new YouzanScrmCustomerCardDeleteParams.YouzanScrmCustomerCardDeleteParamsUser();
            user.setAccountId("");
            user.setAccountType(1);
            params.setUser(user);
            youzanScrmCustomerCardDelete.setAPIParams(a);

            YouzanScrmCustomerCardDeleteResult result = yzClient.invoke(youzanScrmCustomerCardDelete, token, YouzanScrmCustomerCardDeleteResult.class);
            System.out.println(JSON.toJSONString(result));
        }catch (SDKException e){
            e.printStackTrace();
        }
    }

    /**
     *@Description 商家删除会员信息
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void   delRemberBySaler() {
        try {
            //YouZanClient 建议全局唯一,使用 spring 容器管理
            DefaultYZClient yzClient = new DefaultYZClient();
            Token token = new Token(getToken(yzClient).getAccessToken());
            System.out.println(token.getAccessToken());

             //创建参数对象,并设置参数
            YouzanScrmCardDelete youzanScrmCardDelete = new YouzanScrmCardDelete();
            //创建参数对象,并设置参数
            YouzanScrmCardDeleteParams youzanScrmCardDeleteParams = new YouzanScrmCardDeleteParams();
            youzanScrmCardDeleteParams.setCardAlias("Y2g0kvs6kewiid");
            youzanScrmCardDelete.setAPIParams(youzanScrmCardDeleteParams);

            YouzanScrmCardDeleteResult result = yzClient.invoke(youzanScrmCardDelete, token, YouzanScrmCardDeleteResult.class);
            System.out.println(JSON.toJSONString(result));
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
                    .clientId(Content.CLIENT_ID)
                    .clientSecret(Content.CLIENT_SECRET)
                    .grantId(Content.GRANT_ID)
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
