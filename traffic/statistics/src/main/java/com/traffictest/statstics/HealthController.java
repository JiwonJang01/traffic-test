package com.traffictest.statstics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String health(){
        return "health";
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
