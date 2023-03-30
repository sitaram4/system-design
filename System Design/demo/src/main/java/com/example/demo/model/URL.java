package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


import java.sql.Timestamp;

@Data
@Builder
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "url")
//@Table("url")
public class URL {
    @Id
    String id;

    @Column(name="url")
    String url;

    @Column(name = "times_processed")
    Integer timesProcessed;

    @Column(name="content_type")
    String contentType;

    @Column(name = "last_processed")
    Timestamp lastProcessed;
    @Column(name="created_date")
    Timestamp createdDate;


}
