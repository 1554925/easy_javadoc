package com.star.easydoc.service.translator;

import com.alibaba.fastjson.JSON;
import com.example.cloud.project.integrated.common.domain.R;
import com.example.cloud.project.integrated.common.domain.channel.*;
import com.example.cloud.project.integrated.common.domain.channel.impl.AliyunChannel;
import com.example.cloud.project.integrated.common.utils.ObjUtils;
import com.star.easydoc.common.util.HttpUtil;
import com.star.easydoc.config.EasyDocConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gys
 * @date 2023/10/19
 * @description
 */
@Slf4j
public class TranslateRemoteService implements Translator{
    private TranslateChannel translateChannel;
    private EasyDocConfig easyDocConfig;
    private final static String accept = "application/json";
    private final static String contentType = "application/json;charset=utf-8";

    private String sendPost(TranslateChannel translateChannel) {
        try{
            Map<String, String> headers = new HashMap<>();
            // 设置通用的请求属性
            headers.put("Accept", accept);
            headers.put("Content-Type", contentType);
            String body = JSON.toJSONString(translateChannel);
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
            TranslateChannel translateChannel = TranslateFactory.channelFactory(easyDocConfig.getTranslator());
            if(translateChannel instanceof AbstractTranslateChannel){
                AbstractTranslateChannel absTranslate = (AbstractTranslateChannel) translateChannel;
                absTranslate.setSource(text);
                absTranslate.setSourceLanguage(sourceLun);
                absTranslate.setTargetLanguage(targetLun);
                absTranslate.setChannelName(easyDocConfig.getTranslator());
                if(absTranslate.isOnlyKey()){
                    TranslateAppKeyChannel appKeyChannel = (TranslateAppKeyChannel)absTranslate;
                    appKeyChannel.setAppKey(easyDocConfig.getAppKey());
                }else {
                    TranslateAppIdSecretChannel appIdSecretChannel = (TranslateAppIdSecretChannel)absTranslate;
                    appIdSecretChannel.setAppSecret(easyDocConfig.getAppSecret());
                    appIdSecretChannel.setAppId(easyDocConfig.getAppId());
                }
                return sendPost(translateChannel);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
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
