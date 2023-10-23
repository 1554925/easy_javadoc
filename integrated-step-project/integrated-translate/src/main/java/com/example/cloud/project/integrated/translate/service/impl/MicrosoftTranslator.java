package com.example.cloud.project.integrated.translate.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.cloud.project.integrated.common.domain.RemoteTranslateRequest;
import com.example.cloud.project.integrated.common.domain.TranslateChannelType;
import com.example.cloud.project.integrated.common.domain.TranslateResponse;
import com.example.cloud.project.integrated.common.utils.HttpUtils;
import com.example.cloud.project.integrated.translate.service.Translator;
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
@Service(TranslateChannelType.MICROSOFT)
public class MicrosoftTranslator implements Translator {

    private static final String TRANSLATE_URL = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&textType=plain&from=#from#&to=#to#";

    @Override
    public TranslateResponse en2Ch(RemoteTranslateRequest request) {
        request.setFrom("en");
        request.setTo("zh-Hans");
        return translate(request);
    }

    @Override
    public TranslateResponse ch2En(RemoteTranslateRequest request) {
        request.setFrom("zh-Hans");
        request.setTo("en");
        return translate(request);
    }

    private TranslateResponse translate(RemoteTranslateRequest request) {
        String result;
        try {
            String url = TRANSLATE_URL.replace("#from#", request.getFrom()).replace("#to#", request.getTo());
            JSONObject textObject = new JSONObject();
            textObject.put("Text", request.getText());
            JSONArray body = new JSONArray();
            body.add(textObject);
            Map<String, String> headers = new HashMap<>();
            headers.put("Ocp-Apim-Subscription-Key", request.getAppKey());
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
