package com.dropship.uploadshoppeart.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class CommonClient {
  public HttpHeaders getDefaultHeader(){
    // Default header
    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return httpHeaders;
  }
}
