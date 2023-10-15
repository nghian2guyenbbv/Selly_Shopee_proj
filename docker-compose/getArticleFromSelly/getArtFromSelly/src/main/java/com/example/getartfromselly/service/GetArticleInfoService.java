package com.example.getartfromselly.service;

import com.example.getartfromselly.article.ArticleInfoDto;

import java.util.Optional;

public interface GetArticleInfoService {
  Optional<ArticleInfoDto> getArticleInfoFromSelly(String keyWord);
}
