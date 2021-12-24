package com.basic.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TweetApplication {

    public static void main(String[] args) {
        SpringApplication.run(TweetApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

}
