package com.boot.youzan.youzan.as;
/**
 * @projectName Test
 * @description: TODO
 * @author PC
 * @date 2020/8/3013:53
 */

import com.alibaba.fastjson.JSON;
import com.boot.youzan.youzan.Db.GetToken;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.auth.Token;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.core.client.core.YouZanClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;
import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanScrmCardDelete;
import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanScrmCustomerCreate;
import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanScrmCustomerUpdate;
import com.youzan.cloud.open.sdk.gen.v3_0_0.model.*;
import com.youzan.cloud.open.sdk.gen.v3_1_0.api.YouzanScrmCustomerGet;
import com.youzan.cloud.open.sdk.gen.v3_1_0.model.YouzanScrmCustomerGetParams;
import com.youzan.cloud.open.sdk.gen.v3_1_2.api.YouzanScrmCustomerSearch;
import com.youzan.cloud.open.sdk.gen.v3_1_2.model.YouzanScrmCustomerSearchParams;
import com.youzan.cloud.open.sdk.gen.v3_1_2.model.YouzanScrmCustomerSearchResult;
import com.youzan.cloud.open.sdk.gen.v4_0_0.api.YouzanScrmCustomerCardDelete;
import com.youzan.cloud.open.sdk.gen.v4_0_0.model.YouzanScrmCustomerCardDeleteParams;


/***** @description: 客戶信息(注意引入的版本)
　　* @author 王泽辉
　　* @date 2020/8/31 12:02
　　*/
public class YouZanCustomerCardTest {
    public static void main(String[] args) {

         //getToken(new DefaultYZClient());

        // createCustomer();
         //updateCustomer();
          // getCustomer();
        //delRember();

    }
    /**
     *@Description 创建会员
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void   createCustomer() {
        try {
            DefaultYZClient yzClient = new DefaultYZClient();
            Token token = new Token( GetToken.getToken(yzClient).getAccessToken());
            System.out.println(token.getAccessToken());
            YouzanScrmCustomerCreate youzanScrmCustomerCreate = new YouzanScrmCustomerCreate();
            //创建参数对象,并设置参数
            YouzanScrmCustomerCreateParams youzanScrmCustomerCreateParams = new YouzanScrmCustomerCreateParams();
            youzanScrmCustomerCreateParams.setMobile("17702067821");//客户电话

            YouzanScrmCustomerCreateParams.YouzanScrmCustomerCreateParamsCustomercreate  customerParams = new YouzanScrmCustomerCreateParams.YouzanScrmCustomerCreateParamsCustomercreate();
            customerParams.setName("王泽辉");//客户姓名
            customerParams.setBirthday("1997-12-06");//生日
            customerParams.setGender(Short.parseShort("1"));//性别，0：未知；1：男；2：女
            customerParams.setRemark("胡轩同步会员到有赞商城");//备注
            youzanScrmCustomerCreateParams.setCustomerCreate(customerParams);
            customerParams.setAscriptionKdtId(Integer.parseInt(Content.GRANT_ID));
            YouzanScrmCustomerCreateParams.YouzanScrmCustomerCreateParamsContactaddress  addressParams = new YouzanScrmCustomerCreateParams.YouzanScrmCustomerCreateParamsContactaddress();
            addressParams.setAreaCode(430014);//地域编号
            customerParams.setContactAddress(addressParams);

            youzanScrmCustomerCreate.setAPIParams(youzanScrmCustomerCreateParams);

            YouzanScrmCustomerCreateResult response = yzClient.invoke(youzanScrmCustomerCreate, token, YouzanScrmCustomerCreateResult.class);
            if(!response.getSuccess()){
                System.out.println("有赞调用接口失败{}"+ JSON.toJSONString(response));
            }else{
                System.out.println("新增会员同步到有赞接口调用成功{}"+JSON.toJSONString(response));
            }
        }catch (SDKException e){
            e.printStackTrace();
        }
    }
    /**
     *@Description 修改客户信息
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void   updateCustomer() {
        try {
            DefaultYZClient yzClient = new DefaultYZClient();
            Token token = new Token( GetToken.getToken(yzClient).getAccessToken());
            System.out.println(token.getAccessToken());
            YouzanScrmCustomerUpdate youzanScrmCustomerUpdate = new YouzanScrmCustomerUpdate();
            //创建参数对象,并设置参数
            YouzanScrmCustomerUpdateParams youzanScrmCustomerUpdateParams= new YouzanScrmCustomerUpdateParams();
            YouzanScrmCustomerUpdateParams.YouzanScrmCustomerUpdateParamsAccount account=new YouzanScrmCustomerUpdateParams.YouzanScrmCustomerUpdateParamsAccount();
//            account.setAccountId("17702067821");
//            account.setAccountType("Mobile");
            account.setAccountId("8255402861");
            account.setAccountType("YouZanAccount");
            youzanScrmCustomerUpdateParams.setAccount(account);

            YouzanScrmCustomerUpdateParams.YouzanScrmCustomerUpdateParamsCustomerupdate customerParams=new  YouzanScrmCustomerUpdateParams.YouzanScrmCustomerUpdateParamsCustomerupdate();

            customerParams.setName("王泽辉");//客户姓名
            customerParams.setBirthday("1997-12-06");//生日
            customerParams.setGender(Short.parseShort("1"));//性别，0：未知；1：男；2：女
            youzanScrmCustomerUpdateParams.setCustomerUpdate(customerParams);
            customerParams.setAscriptionKdtId(Integer.parseInt(Content.GRANT_ID));
            YouzanScrmCustomerUpdateParams.YouzanScrmCustomerUpdateParamsContactaddress  addressParams = new  YouzanScrmCustomerUpdateParams.YouzanScrmCustomerUpdateParamsContactaddress();
            addressParams.setAreaCode(430014);//地域编号
            customerParams.setContactAddress(addressParams);
            youzanScrmCustomerUpdate.setAPIParams(youzanScrmCustomerUpdateParams);
            YouzanScrmCustomerUpdateResult result = yzClient.invoke(youzanScrmCustomerUpdate, token, YouzanScrmCustomerUpdateResult.class);
            System.out.println(JSON.toJSONString(result));
        }catch (SDKException e){
            e.printStackTrace();
        }
    }
    /**
     *@Description 获取会员列表信息
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void   getCustomerList() {
        try {
           //YouZanClient 建议全局唯一,使用 spring 容器管理
            DefaultYZClient yzClient = new DefaultYZClient();
            Token token = new Token( GetToken.getToken(yzClient).getAccessToken());
            System.out.println(token.getAccessToken());

            YouzanScrmCustomerSearch youzanScrmCustomerSearch = new YouzanScrmCustomerSearch();
             //创建参数对象,并设置参数
            YouzanScrmCustomerSearchParams youzanScrmCustomerSearchParams = new YouzanScrmCustomerSearchParams();
            youzanScrmCustomerSearch.setAPIParams(youzanScrmCustomerSearchParams);
            YouzanScrmCustomerSearchResult result = yzClient.invoke(youzanScrmCustomerSearch, token, YouzanScrmCustomerSearchResult.class);
            System.out.println(JSON.toJSONString(result));
        }catch (SDKException e){
            e.printStackTrace();
        }
    }
    /**
     *@Description 获取会员信息
     *@Author 王泽辉
     *@Date 2020/8/30 13:53
     */
    public static void   getCustomer() {
        try {
            //YouZanClient 建议全局唯一,使用 spring 容器管理
            DefaultYZClient yzClient = new DefaultYZClient();
            Token token = new Token( GetToken.getToken(yzClient).getAccessToken());
            System.out.println(token.getAccessToken());

            YouzanScrmCustomerGet youzanScrmCustomerGet = new YouzanScrmCustomerGet();
             //创建参数对象,并设置参数
            YouzanScrmCustomerGetParams youzanScrmCustomerGetParams = new YouzanScrmCustomerGetParams();
            YouzanScrmCustomerGetParams.YouzanScrmCustomerGetParamsAccount account=new YouzanScrmCustomerGetParams.YouzanScrmCustomerGetParamsAccount();
//            account.setAccountId("8255402861");
//            account.setAccountType("YouZanAccount");
            account.setAccountId("17373131675");
            account.setAccountType("Mobile");
            youzanScrmCustomerGetParams.setAccount(account);
            youzanScrmCustomerGet.setAPIParams(youzanScrmCustomerGetParams);

            YouzanScrmCustomerGetResult result = yzClient.invoke(youzanScrmCustomerGet, token, YouzanScrmCustomerGetResult.class);
            System.out.println(JSON.toJSONString(result));
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
            Token token = new Token(GetToken.getToken(yzClient).getAccessToken());
            System.out.println(token.getAccessToken());

            YouzanScrmCustomerCardDelete youzanScrmCustomerCardDelete = new YouzanScrmCustomerCardDelete();
            //创建参数对象,并设置参数
            YouzanScrmCustomerCardDeleteParams a = new YouzanScrmCustomerCardDeleteParams();

            YouzanScrmCustomerCardDeleteParams.YouzanScrmCustomerCardDeleteParamsParams params=new YouzanScrmCustomerCardDeleteParams.YouzanScrmCustomerCardDeleteParamsParams();
            params.setKdtId(90909493L);
            params.setCardAlias("Y2g0kvs6kewiid");
            YouzanScrmCustomerCardDeleteParams.YouzanScrmCustomerCardDeleteParamsUser user=new YouzanScrmCustomerCardDeleteParams.YouzanScrmCustomerCardDeleteParamsUser();
            user.setAccountId("17601603978");
            user.setAccountType(2);
            params.setUser(user);
            a.setParams(params);
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
            Token token = new Token(GetToken.getToken(yzClient).getAccessToken());
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
}
