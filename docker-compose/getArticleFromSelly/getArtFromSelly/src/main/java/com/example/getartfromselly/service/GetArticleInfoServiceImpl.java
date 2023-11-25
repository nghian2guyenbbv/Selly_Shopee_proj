package com.example.getartfromselly.service;

import com.example.getartfromselly.article.ArticleInfo;
import com.example.getartfromselly.article.ArticleInfoDto;
import com.example.getartfromselly.article.request.GetArtWithKeyWordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class GetArticleInfoServiceImpl implements GetArticleInfoService {

  @Autowired
  private ArticleServiceCommon articleServiceCommon;

  @Autowired
  private GetSellyTokenService getSellyTokenService;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public Optional<ArticleInfoDto> getArticleInfoFromSelly(GetArtWithKeyWordRequest artRequest) {
    String getToken = getSellyTokenService.refreshToken(artRequest.getSellyLogin());
    ResponseEntity<ArticleInfo> response = restTemplate.exchange(
        articleServiceCommon.getArticleInfoUrl(artRequest.getKeyWord()), HttpMethod.GET,
        articleServiceCommon.getDefautEntityForGetRqWithToken(getToken), ArticleInfo.class);
    ArticleInfo artInf = response.getBody();
    return Optional.of(artInf.toArtInfoDto());
  }
}
