package com.example.cloud.project.integrated.common.domain.channel;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 9:25
 */
public interface TranslateChannel {
    boolean isOnlyKey();
    default TranslateChannelType channelType(){
        return TranslateChannelType.channelType(channelName());
    }
    String channelName();
    String getSourceLanguage();
    String getTargetLanguage();
    String getSource();
    void setText(String text);
    String getText();
    String getType();
}
