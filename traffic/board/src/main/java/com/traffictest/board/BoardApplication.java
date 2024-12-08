package com.traffictest.board;

import com.traffictest.common.config.JpaConfiguration;
import com.traffictest.common.config.QueryDslConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@ImportAutoConfiguration
@Import({JpaConfiguration.class, QueryDslConfig.class})
@SpringBootApplication
public class BoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class,args);
    }
}