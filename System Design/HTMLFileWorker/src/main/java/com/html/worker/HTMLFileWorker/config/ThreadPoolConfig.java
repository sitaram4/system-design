package com.html.worker.HTMLFileWorker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

public class ThreadPoolConfig {

    @Bean(name = "urlProcessor")
    public Executor urlExecutorBean(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("url-processor-thread-");
        return executor;
    }
}
