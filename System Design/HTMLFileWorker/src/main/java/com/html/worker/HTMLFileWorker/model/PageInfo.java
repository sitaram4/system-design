package com.html.worker.HTMLFileWorker.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "html_page")
public class PageInfo {
    @Id
    private String id;

    @Field(name = "url_id")
    private String urlId;
    private String url;
    private String title;
    private List<String> keywords;

    private String description;
    private String body;

    @Field(name = "created_time")
    private Timestamp createdTime;
}
