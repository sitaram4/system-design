package com.html.worker.HTMLFileWorker.service;

import com.html.worker.HTMLFileWorker.client.URLFeederService;
import com.html.worker.HTMLFileWorker.client.dao.PageRepository;
import com.html.worker.HTMLFileWorker.model.PageInfo;
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

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Log
@Service
public class URLProcessor {


    @Autowired
    private Retrofit retrofit;

    @Autowired
    private PageRepository pageRepository;
    @Async
    public void process(String url,String urlId){
        try{
            Set<URL> urls = new HashSet<>();
            Document doc = Jsoup.connect(url).get();
//            Elements links = doc.select("a[href]");
//            for(Element link:links){
//                String path = link.attr("href");
//                log.info("Extracted: {} " + path);
//                urls.add(URL.builder().url(path).build());
//            }
            PageInfo pageInfo = fetchMetaInformation(doc);
            pageInfo.setUrlId(urlId);
            pageInfo.setUrl(url);
            log.info("PageInfo: {} " + pageInfo);
            pageRepository.save(pageInfo);
//            Response<Void> response=retrofit.create(URLFeederService.class).batchPublish(urls).execute();
//            if(!response.isSuccessful()){
//                log.info("Retrofit call failed, with response code{}"+response.code());
//            }
        }catch(Exception ex){
            log.warning("Exception: " + ex);
        }

    }
    private PageInfo fetchMetaInformation(Document doc){
        return PageInfo.builder()
                .id(UUID.randomUUID().toString())
                .title(doc.title())
                .description(description(doc))
                .body(body(doc))
                .keywords(keywords(doc))
                .createdTime(new Timestamp(System.currentTimeMillis()))
                .build();
    }
    private String body(Document doc){
        return doc.body().text();
    }
    private String description(Document doc){
        Elements ele = doc.select("meta[name = description]");
        if(!ele.isEmpty()){
            return ele.first().attr("content");
        }
        return null;
    }
    private List<String> keywords(Document doc){
        Elements ele = doc.select("meta[name=keywords]");
        if(!ele.isEmpty()){
            String keywords = ele.first().attr("content");
            return Arrays.asList(keywords.split(","));
        }
        return Collections.emptyList();
    }

}
