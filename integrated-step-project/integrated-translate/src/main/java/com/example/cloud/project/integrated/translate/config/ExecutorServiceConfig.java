package com.example.cloud.project.integrated.translate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author gys
 * @version 1.0
 * @date 2023/8/28 15:55
 */
@Configuration
public class ExecutorServiceConfig {
    @Value("${executor.config.corePoolSize:8}")
    private Integer corePoolSize;

    @Value("${executor.config.maximumPoolSize:20}")
    private Integer maximumPoolSize;

    @Value("${executor.config.keepAliveTime:10}")
    private Long keepAliveTime;

    @Value("${executor.config.queueSize:50}")
    private Integer queueSize;

    @Bean("StepExecutor")
    public ExecutorService executor(){
       return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,
               TimeUnit.MILLISECONDS,
               new ArrayBlockingQueue<Runnable>(queueSize),
               Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());//拒绝策略
    }
}
