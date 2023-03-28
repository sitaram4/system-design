package com.html.worker.HTMLFileWorker.model;


import lombok.*;
import java.sql.Timestamp;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class URL {
    String id;
    String url;

    Integer timesProcessed;

    String contentType;

    Timestamp lastProcessed;
    Timestamp createdDate;


}
