package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.article.ShoppeArticle;
import com.dropship.uploadshoppeart.common.CommonClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopeeArticleImpl extends CommonClient implements ShopeeArticleService {
  @Override
  public String createArticle(String artType) {
    // Call sellyService get article save db
    //Authenticate shopee getToken
    // Call shoppee create articles

    return null;
  }

  @Override
  public boolean deleteArticle() {
    return false;
  }

  @Override
  public List<ShoppeArticle> getAllArticles() {
    return null;
  }
}
