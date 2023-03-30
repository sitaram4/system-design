package com.example.demo.service;

import com.example.demo.codec.URLSerializationCodec;
import com.example.demo.model.URL;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Log
@Service
public class CacheService {

   @Value("${com.urlfeeder.service.cache}")
   private Integer ttl;

   @Value("${com.urlfeeder.service.cache.url}")
    private String url;

   private RedisClient redisClient = null;

   private StatefulRedisConnection<String, URL> statefulRedisConnection = null;

   public URL get(String key){
       URL url = statefulRedisConnection.sync().get(key);
       if(url != null){
           log.info("Serving from cache, for key:{} "+ key);
       }else{
           log.info("Cache miss, for the key:{} "+key);
       }
       return url;
   }

   public void set(URL url){
//       Long ttlSec = TimeUnit.DAYS.toSeconds(this.ttl);
       long ttlSec = 60L;
       statefulRedisConnection.sync().setex(url.getUrl(),ttlSec,url);
   }
   //establish connection
    @PostConstruct
   private void init(){
       log.info("Post Init Called");
       redisClient = RedisClient.create(url);
       statefulRedisConnection = redisClient.connect(new URLSerializationCodec());
   }
   @PreDestroy
   private void destroy(){
       if(statefulRedisConnection!=null){
           statefulRedisConnection.close();
       }
       if(redisClient !=null){
           redisClient.shutdown();
       }
   }
}
