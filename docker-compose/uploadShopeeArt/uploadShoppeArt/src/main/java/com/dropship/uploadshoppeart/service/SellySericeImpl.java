package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.common.CommonClient;
import com.dropship.uploadshoppeart.request.GetArtWithKeyWordRequest;
import com.dropship.uploadshoppeart.request.SellyLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SellySericeImpl extends CommonClient implements SellySerice {
  @Value("${selly.url.getArt}")
  private String SELLY_GET_ART_URL;
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public void getSellyArt(String art) {

    restTemplate.exchange(SELLY_GET_ART_URL, HttpMethod.POST, getSellyArtRequestEntity(art), String.class);
  }

  private HttpEntity getSellyArtRequestEntity(String artType) {
    return new HttpEntity(GetArtWithKeyWordRequest.builder().keyWord(artType)
        .sellyLogin(SellyLoginDto.builder().userName("+84586099640").passWord("123456").build()).build(),
        getDefaultHeader());
  }
}
