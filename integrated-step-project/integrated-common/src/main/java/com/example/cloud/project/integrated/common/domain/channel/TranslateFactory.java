package com.example.cloud.project.integrated.common.domain.channel;

import java.lang.reflect.Constructor;

/**
 * @author gys
 * @date 2023/10/20
 * @description
 */
public class TranslateFactory {

    public static TranslateChannel channelFactory(String name){
        TranslateChannelType type = TranslateChannelType.channelType(name);
        if(type == null){
            return null;
        }
        String clazzName = TranslateChannel.class.getName() + ".impl." + type.name() + "Channel";
        try{
            Class<TranslateChannel> translateChannelClass = (Class<TranslateChannel>) Class.forName(clazzName);
            Constructor<TranslateChannel> constructor = translateChannelClass.getConstructor();
            return constructor.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static TranslateAppIdSecretChannel translateAppIdSecretChannel(String name){
        TranslateChannel channel = channelFactory(name);
        if(channel instanceof TranslateAppIdSecretChannel){
            return (TranslateAppIdSecretChannel)channel;
        }
        return null;
    }

    public static TranslateAppKeyChannel translateAppKeyChannel(String name){
        TranslateChannel channel = channelFactory(name);
        if(channel instanceof TranslateAppIdSecretChannel){
            return (TranslateAppKeyChannel)channel;
        }
        return null;
    }
}
