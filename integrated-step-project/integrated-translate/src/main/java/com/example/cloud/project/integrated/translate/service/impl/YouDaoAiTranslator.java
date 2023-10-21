package com.example.cloud.project.integrated.translate.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.cloud.project.integrated.common.domain.RemoteTranslateRequest;
import com.example.cloud.project.integrated.common.domain.TranslateResponse;
import com.example.cloud.project.integrated.common.utils.HttpUtils;
import com.example.cloud.project.integrated.translate.service.Translator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 有道智云翻译
 *
 * @author wangchao
 * @date 2019/09/01
 */
@Slf4j
@Service("YouDaoAi")
public class YouDaoAiTranslator implements Translator {

    private static final String YOUDAO_URL = "https://openapi.youdao.com/api";


    @Override
    public TranslateResponse en2Ch(RemoteTranslateRequest request) {
        return TranslateResponse.of(translate(request, "en", "zh-CHS"));
    }

    @Override
    public TranslateResponse ch2En(RemoteTranslateRequest request) {
        return TranslateResponse.of(translate(request, "zh-CHS", "en"));
    }

    private String translate(RemoteTranslateRequest request, String from, String to) {
        Map<String, Object> params = new HashMap<>();
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("from", from);
        params.put("to", to);
        params.put("signType", "v3");
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("curtime", curtime);
        String signStr = request.getAppId() + truncate(request.getText()) + salt + curtime + request.getAppSecret();
        String sign = getDigest(signStr);
        params.put("appKey", request.getAppId());
        params.put("q", request);
        params.put("salt", salt);
        params.put("sign", sign);
        String json = null;
        try {
            json = HttpUtils.get(YOUDAO_URL, params);
            YoudaoAiResponse response = JSON.parseObject(json, YoudaoAiResponse.class);
            return Objects.requireNonNull(response).getTranslation().get(0);
        } catch (Exception e) {
            log.error("请求有道智云接口异常,返回为空,response=" + json, e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 生成加密字段
     */
    public static String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }



    private static class YoudaoAiResponse {
        private String errorCode;
        private List<String> translation;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public List<String> getTranslation() {
            return translation;
        }

        public void setTranslation(List<String> translation) {
            this.translation = translation;
        }
    }

}
