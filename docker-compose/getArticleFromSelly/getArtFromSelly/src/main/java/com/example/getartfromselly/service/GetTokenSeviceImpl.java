package com.example.getartfromselly.service;

import com.example.getartfromselly.common.Token;
import com.example.getartfromselly.dto.SellyLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetTokenSeviceImpl implements GetSellyTokenService {
  @Value("${authen.current.token.url}")
  private String GET_CURRENT_TOKEN_URL;

  @Value("${authen.refresh.token.url}")
  private String GET_REFRESH_TOKEN_URL;
  final HttpHeaders httpHeaders = new HttpHeaders();

  private HttpHeaders getDefaultHeader() {
    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return httpHeaders;
  }

  private HttpEntity getLoginEntity(SellyLoginDto sellyUser) {
    SellyLoginDto body = SellyLoginDto.builder().userName(sellyUser.getUserName()).passWord(sellyUser.getPassWord())
        .build();
    return new HttpEntity(body, httpHeaders);

  }

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public String getCurrentToken(SellyLoginDto sellyUserLogin) {
    ResponseEntity<String> currentToken = restTemplate.exchange(GET_CURRENT_TOKEN_URL, HttpMethod.POST,
        getLoginEntity(sellyUserLogin), String.class);
    return currentToken.getBody();
  }

  @Override
  public String refreshToken(SellyLoginDto sellyLoginDto) {
    ResponseEntity<Token> rereshedTokenBody = restTemplate.exchange(GET_REFRESH_TOKEN_URL, HttpMethod.POST,
        getLoginEntity(sellyLoginDto), Token.class);
    return rereshedTokenBody.getBody().getToken();
  }

}
