package com.example.cloud.project.integrated.common.domain.channel;

import lombok.Data;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 10:54
 */
public abstract class AbstractTranslateChannel implements TranslateChannel{
    private String sourceLanguage;
    private String targetLanguage;
    private String text;
    private String source;
    private String channelName;

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    @Override
    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    @Override
    public String getSource() {
        return source;
    }

    public String getChannelName() {
        return channelName();
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String getType() {
        return sourceLanguage+"-"+targetLanguage;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
