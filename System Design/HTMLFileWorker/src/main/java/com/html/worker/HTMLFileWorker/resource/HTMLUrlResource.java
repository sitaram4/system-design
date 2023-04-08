package com.html.worker.HTMLFileWorker.resource;

import com.html.worker.HTMLFileWorker.model.PageInfo;
import com.html.worker.HTMLFileWorker.service.URLProcessor;
import com.html.worker.HTMLFileWorker.service.URLService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@Log
@RestController
public class HTMLUrlResource {

    @Autowired
    private URLProcessor urlProcessor;

    @Autowired
    private URLService urlService;

    @GetMapping("/ping")
    public String ping() {
        return "pong from htmlworker";
    }

    @GetMapping("/{urlId}")
    public ResponseEntity<PageInfo> get(@PathVariable String urlId) {
        Optional<PageInfo> opt = urlService.get(urlId);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(opt.get());
    }

    @PostMapping("url")
    public ResponseEntity<Void> submitUrl(@RequestBody String url){
        log.info("URL recieved: {} " +url);
        urlProcessor.process(url, UUID.randomUUID().toString());
        return ResponseEntity.ok().build();
    }
}
