package com.example.cloud.project.integrated.common.domain;

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
    Baidu(TranslateChannelType.BAIDU,TranslateChannelType.BAIDU_ZH_NAME, "https://api.fanyi.baidu.com/doc/21"),
    YouDaoAi( TranslateChannelType.YOUDAO,TranslateChannelType.YOUDAO_ZH_NAME, "https://ai.youdao.com"),
    Google(TranslateChannelType.GOOGLE,TranslateChannelType.GOOGLE_ZH_NAME,true, "https://cloud.google.com"),
    Microsoft( TranslateChannelType.MICROSOFT,TranslateChannelType.MICROSOFT_ZH_NAME,true, "https://azure.microsoft.com"),
    MicrosoftFree( TranslateChannelType.MICROSOFT_FREE,TranslateChannelType.MICROSOFT_FREE_ZH_NAME, "https://azure.microsoft.com"),
    Tencent( TranslateChannelType.TENCENT,TranslateChannelType.TENCENT_ZH_NAME,true, "https://cloud.tencent.com/document/product/551/7372"),
    Aliyun( TranslateChannelType.ALIYUN, TranslateChannelType.ALIYUN_ZH_NAME, "https://www.aliyun.com/product/ai/alimt"),
    JinShan(TranslateChannelType.JINSHAN,TranslateChannelType.JINSHAN_ZH_NAME, ""),
    Empty("","Empty",false, "");


    private final String code;
    private final String officialName;
    private final String officialApplyUrl;
    private final boolean onlyAppKey;
    TranslateChannelType(String code, String officialName,boolean onlyAppKey, String officialApplyUrl) {
        this.code = code;
        this.officialName = officialName;
        this.onlyAppKey = onlyAppKey;
        this.officialApplyUrl = officialApplyUrl;
    }
    TranslateChannelType(String code, String officialName, String officialApplyUrl) {
        this.code = code;
        this.officialName = officialName;
        this.onlyAppKey = false;
        this.officialApplyUrl = officialApplyUrl;
    }
    public boolean isEmpty(){
        return this == Empty;
    }
    public static TranslateChannelType zhChannelType(String zhName){
        return Arrays.stream(values()).filter(type -> type.getOfficialName().equals(zhName)).findFirst().orElse(Empty);
    }
    public static TranslateChannelType channelType(String name){
        return Arrays.stream(values()).filter(type -> type.name().equals(name)).findFirst().orElse(Empty);
    }



    /**
     * 腾讯翻译
     */
    public static final String TENCENT = "TENCENT";
    /**
     * 百度翻译
     */
    public static final String BAIDU = "BAIDU";
    /**
     * 有道翻译
     */
    public static final String YOUDAO = "YOUDAO";
    /**
     * 金山翻译
     */
    public static final String JINSHAN = "JINSHAN";
    /**
     * 阿里云翻译
     */
    public static final String ALIYUN = "ALIYUN";
    /**
     * 有道智云翻译
     */
    public static final String YOUDAO_AI = "YOUDAO_AI";
    /**
     * 微软翻译
     */
    public static final String MICROSOFT = "MICROSOFT";
    /**
     * 微软免费翻译
     */
    public static final String MICROSOFT_FREE= "MICROSOFT_FREE";
    /**
     * 谷歌翻译
     */
    public static final String GOOGLE = "GOOGLE";

    /**
     * 腾讯翻译
     */
    public static final String TENCENT_ZH_NAME = "腾讯翻译";
    /**
     * 百度翻译
     */
    public static final String BAIDU_ZH_NAME = "百度翻译";
    /**
     * 有道翻译
     */
    public static final String YOUDAO_ZH_NAME = "有道翻译";
    /**
     * 金山翻译
     */
    public static final String JINSHAN_ZH_NAME = "金山翻译";
    /**
     * 阿里云翻译
     */
    public static final String ALIYUN_ZH_NAME = "阿里云翻译";
    /**
     * 有道智云翻译
     */
    public static final String YOUDAO_AI_ZH_NAME = "有道智云翻译";
    /**
     * 微软翻译
     */
    public static final String MICROSOFT_ZH_NAME = "微软翻译";
    /**
     * 微软免费翻译
     */
    public static final String MICROSOFT_FREE_ZH_NAME = "微软免费翻译";
    /**
     * 谷歌翻译
     */
    public static final String GOOGLE_ZH_NAME = "谷歌翻译";
    /**
     * 仅单词分割
     */
    public static final String SIMPLE_SPLITTER = "仅单词分割";
    /**
     * 关闭翻译
     */
    public static final String CLOSE_ZH_NAME = "关闭（只使用自定义翻译）";


    public boolean isOnlyAppKey() {
        return onlyAppKey;
    }
}
