package com.html.worker.HTMLFileWorker.listener;

import com.html.worker.HTMLFileWorker.service.URLProcessor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log
@Service
public class HTMLWorker {
    @Autowired
    private URLProcessor urlProcessor;
    @KafkaListener(topics = "html_topic",groupId = "group_id")
    public void consume(String message){
        log.info("Recieved Message: " + message);
        urlProcessor.process(message);
    }
}
