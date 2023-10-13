package com.example.getartfromselly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetTokenSeviceImpl implements GetSellyTokenService {
  // Default header
  final HttpHeaders httpHeaders = new HttpHeaders();

  private HttpHeaders getDefaultHeader() {
    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return httpHeaders;
  }

  public HttpEntity loginEntity = new HttpEntity(body, getDefaultHeader());
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public String getCurrentToken() {
    return restTemplate.exchange();
  }

  @Override
  public void refreshToken() {
    restTemplate.exchange();
  }
  /*SellyLoginDto body = SellyLoginDto.builder().phone(sellyUser.getUserName()).password(sellyUser.getPassWord()).build();
  HttpEntity loginEntity = new HttpEntity(body, httpHeaders);
        System.out.println("selly login loginEntity: "+loginEntity.toString());
  String token = Strings.EMPTY;
        try{
    ResponseEntity<SellyTokenDto> responseEntity = restTemplate.exchange(SELLY_LOGIN_URL, HttpMethod.POST, loginEntity, SellyTokenDto.class);
    System.out.println("call to selly login");
    token = responseEntity.getBody().getData().getToken();
    System.out.println("token: "+token);
  }catch(
  HttpClientErrorException ex){
    System.out.println("ex: "+ex.getMessage());
    throw new SellyLoginException(ex.getMessage());
  }*/
}
