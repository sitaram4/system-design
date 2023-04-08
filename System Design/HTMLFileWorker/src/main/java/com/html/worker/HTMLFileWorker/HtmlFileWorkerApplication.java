package com.html.worker.HTMLFileWorker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableKafka
@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories
public class HtmlFileWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtmlFileWorkerApplication.class, args);
	}

}
