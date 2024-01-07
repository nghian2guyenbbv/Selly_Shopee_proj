package com.dropship.uploadshoppeart.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CommonClient {

  public static final String COOKIE_PARAM = "cookie";

  public List<String> getSetCookieResponse(ResponseEntity responseEntity) {
    return responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);
  }

  public HttpHeaders getDefaultHeader() {
    // Default header
    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return httpHeaders;
  }
}
