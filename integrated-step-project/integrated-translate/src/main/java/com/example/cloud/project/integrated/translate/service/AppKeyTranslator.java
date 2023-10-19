package com.example.cloud.project.integrated.translate.service;

import com.example.cloud.project.integrated.common.domain.channel.TranslateChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateAppKeyChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateResponse;

/**
 * 翻译
 *
 * @author wangchao
 * @date 2019/11/25
 */
public interface AppKeyTranslator extends Translator{

    /**
     * 英译中
     *
     * @return {@link TranslateResponse}
     */
    TranslateResponse en2Ch(TranslateAppKeyChannel appKeyChannel);

    /**
     * 中译英
     *
     * @return {@link TranslateResponse}
     */
    TranslateResponse ch2En(TranslateAppKeyChannel appKeyChannel);

}
