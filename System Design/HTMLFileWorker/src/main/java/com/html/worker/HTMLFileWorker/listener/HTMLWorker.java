package com.html.worker.HTMLFileWorker.listener;

import com.html.worker.HTMLFileWorker.model.URL;
import com.html.worker.HTMLFileWorker.service.URLProcessor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Log
@Service
public class HTMLWorker {
    @Autowired
    private URLProcessor urlProcessor;
//    @KafkaListener(topics = "html_topic",groupId = "group_id")
    @KafkaListener(topics = "html_topic", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload URL message, @Headers MessageHeaders headers){
        log.info("Recieved Message: " + message + headers);
        urlProcessor.process(message.getUrl(), message.getId());
    }
}
