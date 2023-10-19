//package com.example.cloud.project.integrated.translate.service.impl;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.List;
//
///**
// * 有道翻译
// *
// * @author wangchao
// * @date 2019/09/01
// */
//@Slf4j
//public class YoudaoTranslator extends AbstractTranslator {
//
//    private static final String CH2EN_URL = "http://fanyi.youdao.com/translate?&doctype=json&type=ZH_CN2EN&i=%s";
//    private static final String EN2CH_URL = "http://fanyi.youdao.com/translate?&doctype=json&type=EN2ZH_CN&i=%s";
//    /** 上一次通知时间 */
//    private static long lastNotifyTime = 0L;
//    /** 通知间隔 */
//    private static final long THRESHOLD = 60 * 60 * 1000L;
//
//    @Override
//    public String translateEn2Ch(String text) {
//        log.error("有道免费接口已被官方禁用,请申请私人账号,各大厂商基本都免费");
//        return "";
//    }
//
//    @Override
//    public String translateCh2En(String text) {
//        log.error("有道免费接口已被官方禁用,请申请私人账号,各大厂商基本都免费");
//        return "";
//    }
//
//    private static class YoudaoResponse {
//
//        private String type;
//        private int errorCode;
//        private int elapsedTime;
//        private List<List<TranslateResult>> translateResult;
//
//        public void setType(String type) {
//            this.type = type;
//        }
//
//        public String getType() {
//            return type;
//        }
//
//        public void setErrorCode(int errorCode) {
//            this.errorCode = errorCode;
//        }
//
//        public int getErrorCode() {
//            return errorCode;
//        }
//
//        public void setElapsedTime(int elapsedTime) {
//            this.elapsedTime = elapsedTime;
//        }
//
//        public int getElapsedTime() {
//            return elapsedTime;
//        }
//
//        public void setTranslateResult(List<List<TranslateResult>> translateResult) {
//            this.translateResult = translateResult;
//        }
//
//        public List<List<TranslateResult>> getTranslateResult() {
//            return translateResult;
//        }
//
//    }
//
//    private static class TranslateResult {
//
//        private String src;
//        private String tgt;
//
//        public void setSrc(String src) {
//            this.src = src;
//        }
//
//        public String getSrc() {
//            return src;
//        }
//
//        public void setTgt(String tgt) {
//            this.tgt = tgt;
//        }
//
//        public String getTgt() {
//            return tgt;
//        }
//
//    }
//}
