package com.example.demo.resources;

import com.example.demo.common.Constants;
import com.example.demo.model.URL;
import com.example.demo.service.URLService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Log
@RestController
public class URLResource {

    @Autowired
    private URLService urlService;
    @GetMapping("/ping")
    public String ping(){
        return "ping";
    }

    @PostMapping("/post")
    public ResponseEntity<Void> submitUrl(@RequestBody URL url){
        long startTime = System.currentTimeMillis();
        url.setId(Constants.URL_UUID_PREFIX+UUID.randomUUID().toString());
        url.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        url.setTimesProcessed(0);
        urlService.save(new HashSet<>(){{add(url);}});
        log.info("url"+url);
        log.info("Request processed in {} mills " + (System.currentTimeMillis() - startTime));
        return ResponseEntity.ok().build();
    }
    @PostMapping("/batch")
    public ResponseEntity<Void> submitUrls(@RequestBody Set<URL> urls){
        long startTime = System.currentTimeMillis();
        log.info("Batch request recievd:{}"+urls);
        urls.forEach(u->{
            u.setId(Constants.URL_UUID_PREFIX + UUID.randomUUID().toString());
            u.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            u.setTimesProcessed(0);
        });
        urlService.save(urls);
        log.info("Request processed in {} mills " + (System.currentTimeMillis() - startTime));
        return ResponseEntity.ok().build();
    }
}
