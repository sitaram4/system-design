package com.html.worker.HTMLFileWorker.service;

import com.html.worker.HTMLFileWorker.client.URLFeederService;
import com.html.worker.HTMLFileWorker.model.URL;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.HashSet;
import java.util.Set;

@Log
@Service
public class URLProcessor {


    @Autowired
    private Retrofit retrofit;
    @Async
    public void process(String url){
        try{
            Set<URL> urls = new HashSet<>();
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            for(Element link:links){
                String path = link.attr("href");
                log.info("Extracted: {} " + path);
                urls.add(URL.builder().url(path).build());
            }
            Response<Void> response=retrofit.create(URLFeederService.class).batchPublish(urls).execute();
            if(!response.isSuccessful()){
                log.info("Retrofit call failed, with response code{}"+response.code());
            }
        }catch(Exception ex){
            log.warning("Exception: " + ex);
        }

    }
}
