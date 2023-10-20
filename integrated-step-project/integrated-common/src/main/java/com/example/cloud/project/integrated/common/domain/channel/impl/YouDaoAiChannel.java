package com.example.cloud.project.integrated.common.domain.channel.impl;

import com.example.cloud.project.integrated.common.domain.channel.TranslateAppIdSecretChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateChannelType;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 9:24
 */
public class YouDaoAiChannel extends TranslateAppIdSecretChannel {

    @Override
    public String channelName() {
        return TranslateChannelType.YouDaoAi.getOfficialName();
    }
}