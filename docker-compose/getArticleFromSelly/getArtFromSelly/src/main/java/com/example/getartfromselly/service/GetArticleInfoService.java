package com.example.getartfromselly.service;

import com.example.getartfromselly.article.ArticleInfoDto;
import com.example.getartfromselly.article.request.GetArtWithKeyWordRequest;

import java.util.Optional;

public interface GetArticleInfoService {
  Optional<ArticleInfoDto> getArticleInfoFromSelly(GetArtWithKeyWordRequest artRequest);
}
