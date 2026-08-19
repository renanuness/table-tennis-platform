package com.ttplatform.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableEurekaServer
@EnableConfigServer
public class Main {
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class, args);
    }
}
