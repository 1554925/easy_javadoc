package com.example.cloud.project.integrated.common.domain.channel;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 9:26
 */

public abstract class TranslateAppKeyChannel extends AbstractTranslateChannel {
    private String appKey;

    public boolean isOnlyKey(){
        return true;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
