package com.newsgroup.newsfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // JPA Auditing 활성화
public class NewsfeedApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsfeedApplication.class, args);
    }
}
