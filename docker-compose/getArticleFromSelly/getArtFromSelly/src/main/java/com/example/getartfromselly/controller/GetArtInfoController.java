package com.example.getartfromselly.controller;

import com.example.getartfromselly.article.ArticleInfoDto;
import com.example.getartfromselly.article.request.GetArtWithKeyWordRequest;
import com.example.getartfromselly.entity.ArticleSellyDto;
import com.example.getartfromselly.entity.ProductPhotoUrlDto;
import com.example.getartfromselly.repo.ArticleSellyRepository;
import com.example.getartfromselly.repo.ProductPhotoUrlRepo;
import com.example.getartfromselly.service.GetArticleInfoService;
import com.example.getartfromselly.service.SellyImageService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/sellyArticle")
public class GetArtInfoController {
  // http://localhost:8081/sellyArticle/getArtInfo
/*
  {
    "keyWord":"giay bong ro",
      "sellyLogin":{
    "userName":"+84586099640",
        "passWord":"123456"
  }
  }*/
  @Autowired
  private GetArticleInfoService getArticleInfoService;

  @Autowired
  private ArticleSellyRepository articleSellyRepository;
  @Autowired
  private ProductPhotoUrlRepo productPhotoUrlRepo;

  @Autowired
  private SellyImageService sellyImageService;

  @GetMapping(value= "/downloadImage/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void getImageProduct(@PathVariable("productName") final String productName){
    sellyImageService.downloadImagesAndRename(productName);
  }

/*  @GetMapping(value="/syncImageName")
  public void syncImageNameFromShopee(){
    shopeeService.syncImageName();
  }*/

  @PostMapping("/getArtInfo")
  public ArticleInfoDto getArticleInfoWithKeyWord(@RequestBody GetArtWithKeyWordRequest getArtWithKeyWordRequest) {
    ArticleInfoDto artInfo = getArticleInfoService.getArticleInfoFromSelly(getArtWithKeyWordRequest).orElse(null);
    List<ProductPhotoUrlDto> listProductPhoto = new ArrayList<ProductPhotoUrlDto>();
    AtomicReference<String> description = new AtomicReference<>(" ");
    AtomicReference<String> productName = new AtomicReference<>(" ");
    AtomicReference<Double> productPrice = new AtomicReference<>(NumberUtils.DOUBLE_ZERO);

    artInfo.getSellyPros().forEach(pro -> {
      productName.set(pro.getName());
      pro.getListPhotoUrl().forEach(photo -> {
        ProductPhotoUrlDto proDto = ProductPhotoUrlDto.builder().productName(pro.getName()).photoUrl(photo).build();
        listProductPhoto.add(proDto);
        description.set(pro.getDescription());
        productPrice.set(pro.getPrice());
      });
      ArticleSellyDto articleSellyDto = ArticleSellyDto.builder().productName(productName.get())
          .description(description.get())
          .articleType(getArtWithKeyWordRequest.getKeyWord()).articlePrice(productPrice.get()).build();
      articleSellyRepository.save(articleSellyDto);
      productPhotoUrlRepo.saveAll(listProductPhoto);
    });
    return artInfo;
  }
}
