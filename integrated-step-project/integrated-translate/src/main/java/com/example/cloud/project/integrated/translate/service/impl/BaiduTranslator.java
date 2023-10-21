package com.example.cloud.project.integrated.translate.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.example.cloud.project.integrated.common.domain.RemoteTranslateRequest;
import com.example.cloud.project.integrated.common.domain.TranslateResponse;
import com.example.cloud.project.integrated.common.utils.HttpUtils;
import com.example.cloud.project.integrated.translate.service.Translator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 百度翻译
 *
 * @author wangchao
 * @date 2019/09/01
 */
@Slf4j
@Service("Baidu")
public class BaiduTranslator implements Translator {
    private static final Logger LOGGER = log;
    private static final String TRANSLATE_URL = "http://mt.cn-hangzhou.aliyuncs.com/api/translate/web/ecommerce";
    @Override
    public TranslateResponse en2Ch(RemoteTranslateRequest request) {
        return translate(request);
    }

    @Override
    public TranslateResponse ch2En(RemoteTranslateRequest request) {
        return translate(request);
    }

    private TranslateResponse translate(RemoteTranslateRequest request) {
        String json = null;
        TransResult result = null;
        try {
            String text = request.getText();
            String appId = request.getAppId();
            String secret = request.getAppSecret();

            for (int i = 0; i < 10; i++) {
                String salt = RandomStringUtils.randomNumeric(16);
                String sign = DigestUtils.md5Hex(appId + text + salt + secret);
                String eText = HttpUtils.encode(text);
                json = HttpUtils.get(String.format(TRANSLATE_URL , appId, salt, sign, eText));
                BaiduResponse response = JSON.parseObject(json, BaiduResponse.class);
                if (response == null || "54003".equals(response.getErrorCode())) {
                    Thread.sleep(500);
                } else {
                    result = Objects.requireNonNull(response).getTransResult().get(0);
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            LOGGER.error("请求百度翻译接口异常:请检查本地网络是否可连接外网,也有可能被百度限流,response=" + json, e);
        }
        if(result != null){
            return TranslateResponse.of(result.getDst());
        }
        return null;
    }


    private static class BaiduResponse {
        @JSONField(name = "error_code")
        private String errorCode;
        @JSONField(name = "error_msg")
        private String errorMsg;
        private String from;
        private String to;
        @JSONField(name = "trans_result")
        private List<TransResult> transResult;

        public void setFrom(String from) {
            this.from = from;
        }

        public String getFrom() {
            return from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getTo() {
            return to;
        }

        public void setTransResult(List<TransResult> transResult) {
            this.transResult = transResult;
        }

        public List<TransResult> getTransResult() {
            return transResult;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }

    private static class TransResult {

        private String src;
        private String dst;

        public void setSrc(String src) {
            this.src = src;
        }

        public String getSrc() {
            return src;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }

        public String getDst() {
            return dst;
        }

    }
}
