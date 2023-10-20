//package com.example.cloud.project.integrated.translate.service.impl;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import com.example.cloud.project.integrated.common.utils.HttpUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.Objects;
//
///**
// * 谷歌翻译
// *
// * @author wangchao
// * @date 2023/04/08
// */
//@Slf4j
//public class GoogleTranslator extends AbstractTranslator {
//
//    private static final String EN2CH_URL
//        = "https://translation.googleapis.com/language/translate/v2?q=%s&source=en&target=zh&key=%s&format=text";
//    private static final String CH2EN_URL
//        = "https://translation.googleapis.com/language/translate/v2?q=%s&source=zh&target=en&key=%s&format=text";
//
//    @Override
//    public String translateEn2Ch(String text) {
//        return translate(EN2CH_URL, text);
//    }
//
//    @Override
//    public String translateCh2En(String text) {
//        return translate(CH2EN_URL, text);
//    }
//
//    private String translate(String url, String text) {
//        String json = null;
//        try {
//            json = HttpUtils.get(String.format(url, HttpUtils.encode(text), getConfig().getGoogleKey()), 1000, 3000);
//            JSONObject response = JSON.parseObject(json);
//            return Objects.requireNonNull(response).getJSONObject("data").getJSONArray("translations")
//                .getJSONObject(0).getString("translatedText");
//        } catch (Exception e) {
//            log.error("请求谷歌翻译接口异常:请检查本地网络是否可连接外网(需翻墙),也有可能是国内网络不稳定,response=" + json, e);
//            return StringUtils.EMPTY;
//        }
//    }
//
//}