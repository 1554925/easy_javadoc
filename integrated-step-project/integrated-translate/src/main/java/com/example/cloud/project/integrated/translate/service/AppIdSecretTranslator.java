package com.example.cloud.project.integrated.translate.service;

import com.example.cloud.project.integrated.common.domain.channel.TranslateAppIdSecretChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateResponse;

/**
 * 翻译
 *
 * @author wangchao
 * @date 2019/11/25
 */
public interface AppIdSecretTranslator extends Translator{

    /**
     * 英译中
     *
     * @return {@link TranslateResponse}
     */
    TranslateResponse en2Ch(TranslateAppIdSecretChannel appIdSecretChannel);

    /**
     * 中译英
     *
     * @return {@link TranslateResponse}
     */
    TranslateResponse ch2En(TranslateAppIdSecretChannel appIdSecretChannel);

}
