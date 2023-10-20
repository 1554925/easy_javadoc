package com.example.cloud.project.integrated.translate.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.cloud.project.integrated.common.domain.channel.TranslateAppKeyChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateResponse;
import com.example.cloud.project.integrated.common.utils.HttpUtils;
import com.example.cloud.project.integrated.translate.service.AppKeyTranslator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 微软翻译
 *
 * @author wangchao
 * @date 2023/04/08
 */
@Slf4j
@Service("Microsoft")
public class MicrosoftTranslator implements AppKeyTranslator {

    private static final String EN2CH_URL
        = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&textType=plain&from=en&to=zh-Hans";
    private static final String CH2EN_URL
        = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&textType=plain&from=zh-Hans&to=en";


    @Override
    public TranslateResponse en2Ch(TranslateAppKeyChannel appKeyChannel) {
        return translate(EN2CH_URL, appKeyChannel);
    }

    @Override
    public TranslateResponse ch2En(TranslateAppKeyChannel appKeyChannel) {
        return translate(CH2EN_URL, appKeyChannel);
    }

    private TranslateResponse translate(String url, TranslateAppKeyChannel appKeyChannel) {
        String result;
        try {
            JSONObject textObject = new JSONObject();
            textObject.put("Text", appKeyChannel.getText());
            JSONArray body = new JSONArray();
            body.add(textObject);
            Map<String, String> headers = new HashMap<>();
            headers.put("Ocp-Apim-Subscription-Key", appKeyChannel.getAppKey());
            String json = HttpUtils.postJson(url, headers, JSON.toJSONString(body));
            JSONArray response = JSON.parseArray(json);
            result = Objects.requireNonNull(response).getJSONObject(0).getJSONArray("translations").getJSONObject(0).getString("text");
        } catch (Exception e) {
            log.error("请求微软翻译接口异常:请检查本地网络是否可连接外网,response=", e);
            result = StringUtils.EMPTY;
        }
        return TranslateResponse.of(result);
    }


}
