package com.star.easydoc.service.translator;

import com.alibaba.fastjson.JSON;
import com.example.cloud.project.integrated.common.domain.R;
import com.example.cloud.project.integrated.common.domain.RemoteTranslateRequest;
import com.example.cloud.project.integrated.common.domain.TranslateChannelType;
import com.example.cloud.project.integrated.common.domain.TranslateResponse;
import com.example.cloud.project.integrated.common.utils.ObjUtils;
import com.star.easydoc.common.util.HttpUtil;
import com.star.easydoc.config.EasyDocConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gys
 * @date 2023/10/19
 * @description
 */
public class TranslateRemoteService implements Translator{
    Logger log = LoggerFactory.getLogger(TranslateRemoteService.class);
    private EasyDocConfig easyDocConfig;
    private final static String accept = "application/json";
    private final static String contentType = "application/json;charset=utf-8";

    private String sendPost(RemoteTranslateRequest request) {
        try{
            Map<String, String> headers = new HashMap<>();
            // 设置通用的请求属性
            headers.put("Accept", accept);
            headers.put("Content-Type", contentType);
            String body = JSON.toJSONString(request);
            String responseStr =  HttpUtil.post(easyDocConfig.getProxyUrl(),headers,body);
            R<TranslateResponse> responseR = (R<TranslateResponse>)ObjUtils.copy(responseStr,R.class);
            if(responseR.isSuccess()){
                return responseR.getData().getTarget();
            }
        }catch (Exception e){
            log.error("远程参数请求错误",e);
        }
        return StringUtils.EMPTY;
    }
    @Override
    public String en2Ch(String text) {
        return translate(text,"en","ch");
    }
    @Override
    public String ch2En(String text) {
        return translate(text,"ch","en");
    }


    private String translate(String text,String sourceLun,String targetLun) {
        try{
            TranslateChannelType type = TranslateChannelType.channelType(easyDocConfig.getTranslator());
            if(!type.isEmpty()){
                RemoteTranslateRequest request = new RemoteTranslateRequest();
                request.setFrom(sourceLun);
                request.setTo(targetLun);
                request.setChannelName(easyDocConfig.getTranslator());
                request.setSource(text);
                request.setAppId(easyDocConfig.getAppId());
                request.setAppKey(easyDocConfig.getAppKey());
                request.setAppSecret(easyDocConfig.getAppSecret());
                return sendPost(request);
            }
        }catch (Exception e){
            log.error("远程请求异常：",e);
            throw new RuntimeException("远程请求异常："+e.getMessage());
        }
        throw new RuntimeException("请求参数为空");
    }


    @Override
    public Translator init(EasyDocConfig config) {
        this.easyDocConfig = config;
        return this;
    }

    @Override
    public EasyDocConfig getConfig() {
        return easyDocConfig;
    }

    @Override
    public void clearCache() {

    }
}
