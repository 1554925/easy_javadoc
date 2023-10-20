package com.example.cloud.project.integrated.translate.service;

import com.example.cloud.project.integrated.common.domain.channel.TranslateChannel;
import com.example.cloud.project.integrated.common.domain.channel.TranslateResponse;
import com.example.cloud.project.integrated.common.utils.ObjUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 14:37
 */
@Service
public class TranslateCacheService {
    private final static String PREFIX = "translate:v1:";
    private final static String CACHE = "cache";

    private RMap<String, TranslateResponse> cacheMap ;

    @Autowired
    private RedissonClient redisson;

    @PostConstruct
    public void initialCache() {
        cacheMap = redisson.getMap(PREFIX+CACHE);
    }

    public TranslateResponse getCache(TranslateChannel translateChannel) {
        String source = translateChannel.getSource();
        return cacheMap.get(source);
    }

    public void saveCache(TranslateChannel translateChannel,TranslateResponse response) {
        if(response!=null){
            String source = translateChannel.getSource();
            cacheMap.put(source,response);
        }

    }

}