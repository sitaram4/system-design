package com.example.demo.service;

import com.example.demo.dao.URLRepository;
import com.example.demo.model.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLService {
    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private KafkaService kafkaService;
    public void save(URL url){
        urlRepository.save(url);
        kafkaService.send("html_topic",url.getUrl());
    }
}
