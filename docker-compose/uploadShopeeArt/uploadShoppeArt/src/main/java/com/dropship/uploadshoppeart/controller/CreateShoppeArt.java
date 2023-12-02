package com.dropship.uploadshoppeart.controller;

import com.dropship.uploadshoppeart.article.KeyWord;
import com.dropship.uploadshoppeart.service.ShopeeArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Create article api
//http://localhost:8083/shopee/create-article
/*
    {
        "keyWord": "giay bong ro",
        "limit": 20
        }
        */
@RestController
@RequestMapping(value = "/shopee",  produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class CreateShoppeArt {
  @Autowired
  private ShopeeArticleService shopeeArticleService;

  @PostMapping("/create-article")
  public void createShoppeArt(@RequestBody KeyWord artType) {
    shopeeArticleService.createArticle(artType.getKeyWord(), artType.getLimit());
  }
}

