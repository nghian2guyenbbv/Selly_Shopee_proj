package com.example.getartfromselly.service;

import com.example.getartfromselly.article.ArticleInfo;
import com.example.getartfromselly.article.ArticleInfoDto;
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
    public Optional<ArticleInfoDto> getArticleInfoFromSelly(String keyWord) {
        String getToken = getSellyTokenService.refreshToken().getToken();
        System.out.println("getArticleInfoUrl(keyWord)" + articleServiceCommon.getArticleInfoUrl(keyWord));
        System.out.println("token: " + getToken);
        ResponseEntity<ArticleInfo> response = restTemplate.exchange(articleServiceCommon.getArticleInfoUrl(keyWord),
                HttpMethod.GET, articleServiceCommon.getDefautEntityForGetRqWithToken(getToken), ArticleInfo.class);
        System.out.println("response: " + response.getBody());
        ArticleInfo artInf = response.getBody();
        return Optional.of(artInf.toArtInfoDto());
    }
}
