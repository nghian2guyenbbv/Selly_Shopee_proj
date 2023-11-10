package com.dropship.uploadshoppeart.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
@Configuration
public class ShoppeConfiguration {
  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }
}
