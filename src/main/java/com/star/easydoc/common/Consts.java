package com.star.easydoc.common;

import com.example.cloud.project.integrated.common.domain.TranslateChannelType;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 常量
 *
 * @author wangchao
 * @date 2019/12/08
 */
public class Consts {

    /** 私有构造 */
    private Consts() {}

    /**
     * 基础类型集
     */
    public static final Set<String> BASE_TYPE_SET = Sets.newHashSet("byte", "short", "int", "long", "char", "float",
            "double", "boolean");
    /** 停止词 */
    public static final Set<String> STOP_WORDS = Sets.newHashSet("the", "of");
    /** 默认日期格式 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";

    /** 可用翻译设置 */
    public static final Set<String> ENABLE_ZH_NAME_SET = ImmutableSet.of(
            Consts.BAIDU_TRANSLATOR, Consts.TENCENT_TRANSLATOR,Consts.ALIYUN_TRANSLATOR,Consts.YOUDAO_AI_TRANSLATOR,
            Consts.MICROSOFT_TRANSLATOR,Consts.MICROSOFT_FREE_TRANSLATOR,Consts.GOOGLE_TRANSLATOR,Consts.SIMPLE_SPLITTER,
            Consts.CLOSE_TRANSLATOR);

    /**
     * 腾讯翻译
     */
    public static final String TENCENT_TRANSLATOR = TranslateChannelType.TENCENT_ZH_NAME;
    /**
     * 百度翻译
     */
    public static final String BAIDU_TRANSLATOR = TranslateChannelType.BAIDU_ZH_NAME;

    /**
     * 金山翻译
     */
    public static final String JINSHAN_TRANSLATOR = TranslateChannelType.JINSHAN_ZH_NAME;
    /**
     * 阿里云翻译
     */
    public static final String ALIYUN_TRANSLATOR = TranslateChannelType.ALIYUN_ZH_NAME;
    /**
     * 有道智云翻译
     */
    public static final String YOUDAO_AI_TRANSLATOR = TranslateChannelType.YOUDAO_AI_ZH_NAME;
    /**
     * 微软翻译
     */
    public static final String MICROSOFT_TRANSLATOR = TranslateChannelType.MICROSOFT_ZH_NAME;
    /**
     * 微软免费翻译
     */
    public static final String MICROSOFT_FREE_TRANSLATOR = TranslateChannelType.MICROSOFT_FREE_ZH_NAME;
    /**
     * 谷歌翻译
     */
    public static final String GOOGLE_TRANSLATOR = TranslateChannelType.GOOGLE_ZH_NAME;
    /**
     * 仅单词分割
     */
    public static final String SIMPLE_SPLITTER = "仅单词分割";
    /**
     * 关闭翻译
     */
    public static final String CLOSE_TRANSLATOR = "关闭（只使用自定义翻译）";
}
