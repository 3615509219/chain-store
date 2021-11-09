package com.lq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lq.dao")
public class ChainStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChainStoreApplication.class, args);
    }

}
