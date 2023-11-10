package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.article.ShoppeArticle;
import com.dropship.uploadshoppeart.common.CommonClient;
import com.dropship.uploadshoppeart.dto.ArticleSellyDto;
import com.dropship.uploadshoppeart.repo.ArticleSellyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopeeArticleServiceImpl extends CommonClient implements ShopeeArticleService {
  @Value("shopee.url.createArt")
  private String CREATE_SHOPPE_ART_URL;
  @Autowired
  private SellySerice sellySerice;
  @Autowired
  private ArticleSellyRepo articleSellyRepo;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<String> createArticle(String artType) {
    List<String> shopeeArtIds = new ArrayList<>();
    // Call sellyService get article save db
    sellySerice.getSellyArt(artType);
    // get Article from db
    List<ShoppeArticle> shoppeArt = getAllArtFromDb(artType);
   //Authenticate shopee getToken
    // Call shoppee create articles
    for (ShoppeArticle shoppeArticle : shoppeArt) {
     String shoppeArtId =  createShoppeArt(shoppeArticle);
      shopeeArtIds.add(shoppeArtId);
    }
    return shopeeArtIds;
  }

  @Override
  public boolean deleteArticle() {
    return false;
  }

  @Override
  public List<ShoppeArticle> getAllArticles() {
    return null;
  }

  @Override
  public List<ShoppeArticle> getAllArtFromDb(String artType) {
    List<ArticleSellyDto> sellyArts = articleSellyRepo.findArticleSellyDtosByArticleType(artType);
    return sellyArts.stream().map(sellyArt->{
      return ShoppeArticle.builder().artName(sellyArt.getProductName()).price(1000000.00)
          .description(sellyArt.getDescription()).build();
    }).collect(Collectors.toList());
  }

  @Override
  public String createShoppeArt(ShoppeArticle shopeeArticle) {
    ResponseEntity<ArtShopeeRespone> createShopeeArtRp =  restTemplate.exchange(CREATE_SHOPPE_ART_URL, HttpMethod.POST, getCreateArtEntity(shopeeArticle), String.class);
  }

  private HttpEntity getCreateArtEntity(ShoppeArticle shopeeArticle) {
/*    SellyLoginDto body = SellyLoginDto.builder().userName(sellyUser.getUserName()).passWord(sellyUser.getPassWord())
        .build();*/

    return new HttpEntity(body, getDefaultHeader());
  }
}
