package com.example.getartfromselly.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;

@Component
public class ArticleServiceCommon {
    private static final String GET_ARTICLE_INFO_URL = "https://app-api.selly.vn/products?limit=20&keyword=%s&isAvailable=true";

    private static final String BEARER = "Bearer ";

    public String getArticleInfoUrl(String keyWord) {
        return String.format(GET_ARTICLE_INFO_URL, formatKeyWord(keyWord));
    }

    public String formatKeyWord(String rawKeyWord) {
        return StringUtils.replace(rawKeyWord.trim(), " ", "%20");
    }

    public HttpHeaders getDefaultHeader(String token) {
        // Default header
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken().apply(token));
        return httpHeaders;
    }


    private Function<String, String> getBearerToken() {
        return token -> BEARER + token;

    }

    public HttpEntity getDefautEntityForGetRqWithToken(String token) {
        return new HttpEntity(getDefaultHeader(token));
    }

}
