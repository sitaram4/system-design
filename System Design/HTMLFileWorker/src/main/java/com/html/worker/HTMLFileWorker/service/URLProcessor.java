package com.html.worker.HTMLFileWorker.service;

import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log
@Service
public class URLProcessor {


    @Async
    public void process(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            for(Element link:links){
                log.info("Extracted: {} " + link.attr("href"));
            }
        }catch(Exception ex){
            log.warning("Exception: " + ex);
        }

    }
}
