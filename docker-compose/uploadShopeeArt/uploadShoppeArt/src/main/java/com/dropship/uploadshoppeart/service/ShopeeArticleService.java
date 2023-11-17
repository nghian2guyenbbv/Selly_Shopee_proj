package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.article.ShoppeArticle;

import java.util.List;

public interface ShopeeArticleService {
  public List<String> createArticle(String artType, int limit);

  public boolean deleteArticle();

  public List<ShoppeArticle> getAllArticles();

  public List<ShoppeArticle> getAllArtFromDb(String artType);

  public String createShoppeArt(ShoppeArticle shopeeArticle);

}
