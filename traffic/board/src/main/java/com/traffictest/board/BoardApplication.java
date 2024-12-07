package com.traffictest.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(com.traffictest.common.config.JpaConfiguration.class)
@SpringBootApplication
public class BoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class,args);
    }
}