package com.example.cloud.project.integrated.common.domain.channel;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 9:33
 * - 百度翻译申请地址：<https://api.fanyi.baidu.com/doc/21>
 * - 腾讯翻译申请地址：<https://cloud.tencent.com/document/product/551/7372>
 * - 阿里云翻译申请地址：<https://www.aliyun.com/product/ai/alimt>
 * - 有道智云翻译申请地址：<https://ai.youdao.com/>
 * - 微软翻译申请地址：<https://azure.microsoft.com/>
 * - 谷歌翻译（需要翻墙）申请地址：<https://cloud.google.com/>
 */
@Getter
public enum TranslateChannelType {
    Baidu("http://api.fanyi.baidu.com", "/api/trans/vip/translate?from=auto&to=auto&appid=%s&salt=%s&sign=%s&q=%s" , TranslateChannelType.BAIDU_TRANSLATOR, "https://api.fanyi.baidu.com/doc/21"),
    YouDaoAi("https://ai.youdao.com", "" , TranslateChannelType.YOUDAO_TRANSLATOR, "https://ai.youdao.com"),
    Google("https://cloud.google.com", "" , TranslateChannelType.GOOGLE_TRANSLATOR, "https://cloud.google.com"),
    Microsoft("https://azure.microsoft.com", "" , TranslateChannelType.MICROSOFT_TRANSLATOR, "https://azure.microsoft.com"),
    MicrosoftFree("https://azure.microsoft.com", "" , TranslateChannelType.MICROSOFT_FREE_TRANSLATOR, "https://azure.microsoft.com"),
    Tencent("https://cloud.tencent.com", "" , TranslateChannelType.TENCENT_TRANSLATOR, "https://cloud.tencent.com/document/product/551/7372"),
    Aliyun("http://mt.cn-hangzhou.aliyuncs.com", "/api/translate/web/ecommerce" , TranslateChannelType.ALIYUN_TRANSLATOR, "https://www.aliyun.com/product/ai/alimt"),
    JinShan("http://dict-co.iciba.com", "" , TranslateChannelType.JINSHAN_TRANSLATOR, "");


    private final String officialHost;
    private final String officialUri;
    private final String officialName;
    private final String officialApplyUrl;
    TranslateChannelType(String officialHost, String officialUri, String officialName, String officialApplyUrl) {
        this.officialHost = officialHost;
        this.officialUri = officialUri;
        this.officialName = officialName;
        this.officialApplyUrl = officialApplyUrl;
    }
    public String getTranslateUrl(){
        return this.officialHost+this.officialUri;
    }

    public static TranslateChannelType channelType(String name){
        return Arrays.stream(values()).filter(type -> type.getOfficialName().equals(name)).findFirst().orElse(null);
    }



    /**
     * 腾讯翻译
     */
    public static final String TENCENT_TRANSLATOR = "腾讯翻译";
    /**
     * 百度翻译
     */
    public static final String BAIDU_TRANSLATOR = "百度翻译";
    /**
     * 有道翻译
     */
    public static final String YOUDAO_TRANSLATOR = "有道翻译";
    /**
     * 金山翻译
     */
    public static final String JINSHAN_TRANSLATOR = "金山翻译";
    /**
     * 阿里云翻译
     */
    public static final String ALIYUN_TRANSLATOR = "阿里云翻译";
    /**
     * 有道智云翻译
     */
    public static final String YOUDAO_AI_TRANSLATOR = "有道智云翻译";
    /**
     * 微软翻译
     */
    public static final String MICROSOFT_TRANSLATOR = "微软翻译";
    /**
     * 微软免费翻译
     */
    public static final String MICROSOFT_FREE_TRANSLATOR = "微软免费翻译";
    /**
     * 谷歌翻译
     */
    public static final String GOOGLE_TRANSLATOR = "谷歌翻译";
    /**
     * 仅单词分割
     */
    public static final String SIMPLE_SPLITTER = "仅单词分割";
    /**
     * 关闭翻译
     */
    public static final String CLOSE_TRANSLATOR = "关闭（只使用自定义翻译）";



}
