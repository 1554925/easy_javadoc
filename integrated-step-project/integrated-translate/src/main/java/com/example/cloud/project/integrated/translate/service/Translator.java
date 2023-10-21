package com.example.cloud.project.integrated.translate.service;

import com.example.cloud.project.integrated.common.domain.RemoteTranslateRequest;
import com.example.cloud.project.integrated.common.domain.TranslateResponse;

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
    TranslateResponse en2Ch(RemoteTranslateRequest request);


    /**
     * 中译英
     *
     * @return {@link TranslateResponse}
     */
    TranslateResponse ch2En(RemoteTranslateRequest translateChannel);
}
