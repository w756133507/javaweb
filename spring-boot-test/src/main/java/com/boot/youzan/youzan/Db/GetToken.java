package com.boot.youzan.youzan.Db;

import com.boot.youzan.youzan.as.Content;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.core.YouZanClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;

/**
 * @Description TODO
 * @Author 王泽辉
 * @Date 2020/11/20 14:45
 */
public class GetToken {
    public static OAuthToken getToken(YouZanClient yzClient) {
        try {
            TokenParameter tokenParameter = TokenParameter.self()
                    .clientId(Content.CLIENT_ID)
                    .clientSecret(Content.CLIENT_SECRET)
                    .grantId(Content.GRANT_ID)
                    .refresh(false)
                    .build();
            OAuthToken oAuthToken = yzClient.getOAuthToken(tokenParameter);
            System.out.println("获取token" + oAuthToken);
            return oAuthToken;
        } catch (SDKException e) {
            e.printStackTrace();
        }
        return null;
    }
}
