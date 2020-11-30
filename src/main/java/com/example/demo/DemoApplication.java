package com.example.demo;

import me.whiteship.Holoman;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    public Holoman holoman() {
//        Holoman holoman = new Holoman();
//        holoman.setName("whiteship");
//        holoman.setHowLong(60);
//        return holoman;
//    }

}
