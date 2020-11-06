package com.youzan.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

/**
 * @author Changrong Yang
 * @version 1.0
 * @date 2020/4/22 9:18
 */
public class WechatResponseErrorHandler extends DefaultResponseErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(WechatResponseErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = getHttpStatusCode(response);
        switch (statusCode.series()) {
            case CLIENT_ERROR:
                logger.error("调用wechat接口失败，状态码：[{}]，状态描述：[{}]", statusCode, response.getStatusText());
                throw new HttpClientErrorException(statusCode, response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            case SERVER_ERROR:
                logger.error("调用wechat接口失败，状态码：[{}]，状态描述：[{}]", statusCode, response.getStatusText());
                throw new HttpServerErrorException(statusCode, response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            default:
                logger.error("Unknown status code [{}]", statusCode);
                throw new RestClientException("Unknown status code [" + statusCode + "]");
        }
    }
}
