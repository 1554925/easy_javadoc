//package com.example.cloud.project.integrated.translate.service.impl;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONArray;
//import com.alibaba.fastjson2.JSONObject;
//import com.example.cloud.project.integrated.common.utils.HttpUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * 微软翻译
// *
// * @author wangchao
// * @date 2023/04/08
// */
//@Slf4j
//public class MicrosoftTranslator extends AbstractTranslator {
//
//    private static final String EN2CH_URL
//        = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&textType=plain&from=en&to=zh-Hans";
//    private static final String CH2EN_URL
//        = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&textType=plain&from=zh-Hans&to=en";
//
//    @Override
//    protected String translateCh2En(String text) {
//        return translate(CH2EN_URL, text);
//    }
//
//    @Override
//    protected String translateEn2Ch(String text) {
//        return translate(EN2CH_URL, text);
//    }
//
//    private String translate(String url, String text) {
//        String json = null;
//        try {
//            JSONObject textObject = new JSONObject();
//            textObject.put("Text", text);
//            JSONArray body = new JSONArray();
//            body.add(textObject);
//            Map<String, String> headers = new HashMap<>();
//            headers.put("Ocp-Apim-Subscription-Key", getConfig().getMicrosoftKey());
//            json = HttpUtils.postJson(url, headers, JSON.toJSONString(body));
//            JSONArray response = JSON.parseArray(json);
//            return Objects.requireNonNull(response).getJSONObject(0).getJSONArray("translations").getJSONObject(0).getString("text");
//        } catch (Exception e) {
//            log.error("请求微软翻译接口异常:请检查本地网络是否可连接外网,response=" + json, e);
//            return StringUtils.EMPTY;
//        }
//    }
//}
