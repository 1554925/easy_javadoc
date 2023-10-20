package com.star.easydoc.service.translator;

import com.alibaba.fastjson.JSON;
import com.example.cloud.project.integrated.common.domain.channel.TranslateChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateFactory;
import com.example.cloud.project.integrated.common.domain.channel.impl.AliyunChannel;
import com.star.easydoc.common.util.HttpUtil;
import com.star.easydoc.config.EasyDocConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gys
 * @date 2023/10/19
 * @description
 */
public class TranslateRemoteService implements Translator{
    private TranslateChannel translateChannel;
    private EasyDocConfig easyDocConfig;
    private final static String accept = "application/json";
    private final static String contentType = "application/json;charset=utf-8";

    private String sendPost(TranslateChannel translateChannel) {
        Map<String, String> headers = new HashMap<>();
        // 设置通用的请求属性
        headers.put("Accept", accept);
        headers.put("Content-Type", contentType);
        String body = JSON.toJSONString(translateChannel);
        return HttpUtil.post(easyDocConfig.getProxyUrl(),headers,body);
    }
    @Override
    public String en2Ch(String text) {
        TranslateChannel translateChannel = TranslateFactory.translateAppKeyChannel();


        return null;
    }

    @Override
    public String ch2En(String text) {
        return null;
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
