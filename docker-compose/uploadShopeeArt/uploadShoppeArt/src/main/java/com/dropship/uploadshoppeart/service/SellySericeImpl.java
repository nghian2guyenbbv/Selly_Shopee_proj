package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.common.CommonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SellySericeImpl extends CommonClient implements SellySerice{
  @Value("selly.url.getArt")
  private String SELLY_GET_ART_URL;
  @Autowired
  private RestTemplate restTemplate;
  @Override
  public void getSellyArt(String art) {

    HttpHeaders httpHeaders = getDefaultHeader();
  }
}
