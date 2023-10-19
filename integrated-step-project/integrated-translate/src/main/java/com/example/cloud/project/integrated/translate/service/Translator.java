package com.example.cloud.project.integrated.translate.service;

import com.example.cloud.project.integrated.common.domain.channel.TranslateAppIdSecretChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateAppKeyChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateResponse;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 15:54
 */
public interface Translator {

    /**
     * 英译中
     *
     * @return {@link TranslateResponse}
     */
    default TranslateResponse en2Ch(TranslateChannel translateChannel){
        if(translateChannel.isOnlyKey()){
            TranslateAppKeyChannel channel = (TranslateAppKeyChannel)translateChannel;
            AppIdSecretTranslator translator = (AppIdSecretTranslator)this;
            return translator.en2Ch(channel);
        }else {
            TranslateAppIdSecretChannel channel = (TranslateAppIdSecretChannel)translateChannel;
            AppKeyTranslator translator = (AppKeyTranslator)this;
            return translator.en2Ch(channel);
        }

    }


    /**
     * 中译英
     *
     * @return {@link TranslateResponse}
     */
    default TranslateResponse ch2En(TranslateChannel translateChannel){
        if(translateChannel.isOnlyKey()){
            TranslateAppKeyChannel channel = (TranslateAppKeyChannel)translateChannel;
            AppIdSecretTranslator translator = (AppIdSecretTranslator)this;
            return translator.ch2En(channel);
        }else {
            TranslateAppIdSecretChannel channel = (TranslateAppIdSecretChannel)translateChannel;
            AppKeyTranslator translator = (AppKeyTranslator)this;
            return translator.ch2En(channel);
        }
    }
}
