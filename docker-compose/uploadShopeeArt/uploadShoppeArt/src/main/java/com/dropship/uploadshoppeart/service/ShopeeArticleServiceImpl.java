package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.article.ShoppeArticle;
import com.dropship.uploadshoppeart.common.CommonClient;
import com.dropship.uploadshoppeart.dto.ArticleSellyDto;
import com.dropship.uploadshoppeart.repo.ArticleSellyRepo;
import com.dropship.uploadshoppeart.request.CreateArtShopeeRq;
import com.dropship.uploadshoppeart.response.CreateShopeeArtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopeeArticleServiceImpl extends CommonClient implements ShopeeArticleService {
  @Value("${shopee.url.createArt}")
  private String CREATE_SHOPPE_ART_URL;

  @Autowired
  private ShopeeLoginWithSpc shopeeLoginWithSpc;

  private String getShopeeCookie() {
    String defaultCookie = "SPC_SC_TK=$SPC_TOKEN; SPC_SC_UD=113891819";
    String finalShopeeToken = defaultCookie.replace("$SPC_TOKEN", shopeeLoginWithSpc.getSpcToken());
    return finalShopeeToken;
  }

  @Autowired
  private SellySerice sellySerice;
  @Autowired
  private ArticleSellyRepo articleSellyRepo;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<String> createArticle(String artType, int limit) {
    List<String> shopeeArtIds = new ArrayList<>();
    // Call sellyService get article save db
    sellySerice.getSellyArt(artType);
    // get Article from db
    List<ShoppeArticle> shoppeArt = getAllArtFromDb(artType);
    //Authenticate shopee getToken
    // Call shoppee create articles
    int index = 0;
    for (ShoppeArticle shoppeArticle : shoppeArt) {
      if (index < limit) {
        String shoppeArtId = createShoppeArt(shoppeArticle);
        shopeeArtIds.add(shoppeArtId);
        index++;
      }
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
    return sellyArts.stream().map(sellyArt -> {
      return ShoppeArticle.builder().artName(sellyArt.getProductName()).price(sellyArt.getPrice())
          .description(sellyArt.getDescription()).build();
    }).collect(Collectors.toList());
  }

  @Override
  public String createShoppeArt(ShoppeArticle shopeeArticle) {
    ResponseEntity<CreateShopeeArtResponse> createShopeeArtRp = restTemplate.exchange(CREATE_SHOPPE_ART_URL,
        HttpMethod.POST, getCreateArtEntity(shopeeArticle), CreateShopeeArtResponse.class);
    if(createShopeeArtRp.getBody().getCode()!=0){
      System.out.println("ERROR: "+createShopeeArtRp.getBody().getMsg());
    }
    return null;
  }

  private HttpEntity getCreateArtEntity(ShoppeArticle shopeeArticle) {

    HttpHeaders header = getDefaultHeader();
    header.set("cookie", getShopeeCookie());
    String request = CreateArtShopeeRq.tuiDeoNuRequest;
    String formatedRequest = request.replace("$ARTICLE_NAME", shopeeArticle.getArtName())
        .replace("$ARTICLE_DESCRIPTION", FormatmessageShopeeRule.formatMessage(shopeeArticle.getDescription()));
    ;
    System.out.println("request: " + formatedRequest);
    return new HttpEntity(formatedRequest, header);
  }
}
