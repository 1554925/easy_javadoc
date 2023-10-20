package com.example.cloud.project.integrated.common.domain.channel.impl;

import com.example.cloud.project.integrated.common.domain.channel.TranslateChannelType;
import com.example.cloud.project.integrated.common.domain.channel.TranslateAppKeyChannel;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 9:24
 */
public class MicrosoftChannel extends TranslateAppKeyChannel {

    @Override
    public String channelName() {
        return TranslateChannelType.Microsoft.getOfficialName();
    }
}
