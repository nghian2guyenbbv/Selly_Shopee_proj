package com.dropship.uploadshoppeart.controller;

import com.dropship.uploadshoppeart.article.ShoppeArticle;
import com.dropship.uploadshoppeart.service.ShopeeArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopee")
public class CreateShoppeArt {
  @Autowired
  private ShopeeArticleService shopeeArticleService;
  @GetMapping("/create-article")
  public void createShoppeArt(String artType){
    shopeeArticleService.createArticle(artType);
  }
}
