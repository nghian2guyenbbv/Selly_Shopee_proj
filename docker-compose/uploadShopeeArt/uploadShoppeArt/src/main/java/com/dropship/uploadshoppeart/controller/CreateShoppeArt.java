package com.dropship.uploadshoppeart.controller;

import com.dropship.uploadshoppeart.article.KeyWord;
import com.dropship.uploadshoppeart.service.ShopeeArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopee")
public class CreateShoppeArt {
  @Autowired
  private ShopeeArticleService shopeeArticleService;
  @PostMapping("/create-article")
  public void createShoppeArt(KeyWord artType){
    shopeeArticleService.createArticle(artType.getKeyWord());
  }
}
