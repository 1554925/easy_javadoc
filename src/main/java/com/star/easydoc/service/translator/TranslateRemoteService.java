package com.star.easydoc.service.translator;

import com.example.cloud.project.integrated.common.domain.channel.TranslateChannel;
import com.star.easydoc.config.EasyDocConfig;

/**
 * @author gys
 * @date 2023/10/19
 * @description
 */
public class TranslateRemoteService implements Translator{
    private TranslateChannel translateChannel;
    private EasyDocConfig easyDocConfig;

    @Override
    public String en2Ch(String text) {

        return null;
    }

    @Override
    public String ch2En(String text) {
        return null;
    }

    @Override
    public Translator init(EasyDocConfig config) {
        this.easyDocConfig = config;
        return this;
    }

    @Override
    public EasyDocConfig getConfig() {
        return easyDocConfig;
    }

    @Override
    public void clearCache() {

    }
}
