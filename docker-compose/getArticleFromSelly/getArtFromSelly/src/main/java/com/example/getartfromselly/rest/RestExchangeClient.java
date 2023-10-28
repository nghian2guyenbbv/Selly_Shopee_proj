package com.example.getartfromselly.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class RestExchangeClient {
  private String SPACE = " ";
  private String KEY = "%";
  public HttpEntity toEntityNoBody(HttpHeaders httpHeaders) {
    return new HttpEntity(httpHeaders);
  }

  public String toGetArtInfoUrl(String url, String keyWord){
    return url.concat(keyWord.replace(SPACE, KEY));
  };
}
