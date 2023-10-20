package com.example.cloud.project.integrated.translate.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.cloud.project.integrated.common.domain.channel.TranslateAppIdSecretChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateAppKeyChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateChannelType;
import com.example.cloud.project.integrated.common.domain.channel.TranslateResponse;
import com.example.cloud.project.integrated.common.utils.HttpUtils;
import com.example.cloud.project.integrated.translate.service.AppIdSecretTranslator;
import com.example.cloud.project.integrated.translate.service.AppKeyTranslator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 谷歌翻译
 *
 * @author wangchao
 * @date 2023/04/08
 */
@Slf4j
@Service("Google")
public class GoogleTranslator implements AppKeyTranslator {

    private static final String EN2CH_URL
        = "https://translation.googleapis.com/language/translate/v2?q=%s&source=en&target=zh&key=%s&format=text";
    private static final String CH2EN_URL
        = "https://translation.googleapis.com/language/translate/v2?q=%s&source=zh&target=en&key=%s&format=text";

    @Override
    public TranslateResponse en2Ch(TranslateAppKeyChannel channel) {
        return translate(EN2CH_URL, channel);
    }

    @Override
    public TranslateResponse ch2En(TranslateAppKeyChannel channel) {
        return translate(CH2EN_URL, channel);
    }

    private TranslateResponse translate(String url, TranslateAppKeyChannel channel) {
        String json = null;
        String translatedText;
        try {
            json = HttpUtils.get(String.format(url, HttpUtils.encode(channel.getText()),
                    channel.getAppKey()), 1000, 3000);
            JSONObject response = JSON.parseObject(json);
            translatedText = Objects.requireNonNull(response).getJSONObject("data").getJSONArray("translations")
                .getJSONObject(0).getString("translatedText");
        } catch (Exception e) {
            log.error("请求谷歌翻译接口异常:请检查本地网络是否可连接外网(需翻墙),也有可能是国内网络不稳定,response=" + json, e);
            translatedText = StringUtils.EMPTY;
        }
        return TranslateResponse.of(translatedText);
    }

}
