package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.article.ShoppeArticle;

import java.util.List;

public interface ShopeeArticleService {
  public String createArticle(String artType);
  public boolean deleteArticle();
  public List<ShoppeArticle> getAllArticles();

}
