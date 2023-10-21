package com.example.cloud.project.integrated.common.domain;

import lombok.Data;

/**
 * @author gys
 * @date 2023/10/20
 * @description
 */
@Data
public class RemoteTranslateRequest {
    private String from;
    private String to;
    private String source;
    private String channelName;
    private String appId;
    private String text;
    private String appSecret;
    private String appKey;


    public TranslateChannelType channelType(){
        return TranslateChannelType.channelType(channelName);
    }

    public boolean isOnlyAppKey(){
        return TranslateChannelType.channelType(channelName).isOnlyAppKey();
    }



}
