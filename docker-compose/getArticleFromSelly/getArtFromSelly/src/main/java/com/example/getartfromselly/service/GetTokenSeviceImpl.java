package com.example.getartfromselly.getToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetTokenSeviceImpl implements GetSellyTokenService{
  @Autowired
  private RestTemplate restTemplate;
  @Override
  public String getTokenService() {
    return null;
  }
}
