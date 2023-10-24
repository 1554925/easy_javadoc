package com.example.cloud.project.integrated.translate.service;

import com.example.cloud.project.integrated.common.domain.TranslateResponse;
import com.example.cloud.project.integrated.common.utils.Md5CryptUtils;
import com.example.cloud.project.integrated.common.domain.RemoteTranslateRequest;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/19 14:37
 */
@Service
@RefreshScope
public class TranslateCacheService {
    private final static String PREFIX = "translate:v1:";
    private final static String CACHE = "cache";
    private static final Map<String, TranslateResponse> localCacheMap = new ConcurrentHashMap<>();

    private RMap<String, TranslateResponse> cacheMap ;

    @Value("translate.cache.type:local")
    private String type;

    @Autowired
    private RedissonClient redisson;

    @PostConstruct
    public void initialCache() {
        cacheMap = redisson.getMap(PREFIX+CACHE);
    }

    public TranslateResponse getCache(RemoteTranslateRequest request) {
        String source = request.getSource();
        return this.get(source);
    }

    public void saveCache(RemoteTranslateRequest request, TranslateResponse response) {
        if(response!=null){
            String source = request.getSource();
            this.put(source,response);
        }
    }

    public TranslateResponse get(String source){
        String key = Md5CryptUtils.md5(source.getBytes(StandardCharsets.UTF_8));
        if("local".equalsIgnoreCase(type)){
            return localCacheMap.get(key);
        }else {
            return cacheMap.get(key);
        }
    }

    public void put(String source,TranslateResponse response){
        String key = Md5CryptUtils.md5(source.getBytes(StandardCharsets.UTF_8));
        if("local".equalsIgnoreCase(type)){
            localCacheMap.put(key,response);
        }else {
            cacheMap.put(key,response);
        }
    }

}
