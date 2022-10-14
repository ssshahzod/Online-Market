package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class OnlineMarket {

    public static void main(String[] args) {
        SpringApplication.run(OnlineMarket.class, args);
    }

}
