package com.example.cloud.project.integrated.common.domain.channel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 9:27
 */
public abstract class TranslateAppIdSecretChannel extends AbstractTranslateChannel {
    private String appId;
    private String appSecret;

    public boolean isOnlyKey(){
        return false;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
