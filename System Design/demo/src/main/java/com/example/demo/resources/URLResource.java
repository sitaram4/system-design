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
        url.setId(Constants.URL_UUID_PREFIX+UUID.randomUUID().toString());
        url.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        urlService.save(url);
        log.info("url"+url);
        return ResponseEntity.ok().build();
    }
}
