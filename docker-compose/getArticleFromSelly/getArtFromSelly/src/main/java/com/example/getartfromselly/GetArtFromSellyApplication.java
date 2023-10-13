package com.example.getartfromselly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GetArtFromSellyApplication {
  @Bean
  public RestTemplate getRest() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(GetArtFromSellyApplication.class, args);
  }

}
