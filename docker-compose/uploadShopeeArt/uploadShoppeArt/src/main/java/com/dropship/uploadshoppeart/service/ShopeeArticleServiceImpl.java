package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.article.ShoppeArticle;
import com.dropship.uploadshoppeart.common.CommonClient;
import com.dropship.uploadshoppeart.dto.ArticleSellyDto;
import com.dropship.uploadshoppeart.repo.ArticleSellyRepo;
import com.dropship.uploadshoppeart.request.CreateArtShopeeRq;
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
      //ShoppeArticle shoppeArticle = ShoppeArticle.builder().artName("tui ao nu").description("tui ao nu").build();
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
    ResponseEntity<String> createShopeeArtRp = restTemplate.exchange(CREATE_SHOPPE_ART_URL, HttpMethod.POST,
        getCreateArtEntity(shopeeArticle), String.class);
    System.out.println("createShopeeArtRp" + createShopeeArtRp);
    return null;
  }

  private HttpEntity getCreateArtEntity(ShoppeArticle shopeeArticle) {

    HttpHeaders header = getDefaultHeader();
    header.set("cookie",
        "SPC_F=RIl4pQ7UBsEtWaey2oOtSKyIiI6FrSdg; REC_T_ID=6b00daf9-9732-11ed-8ad5-3473791746da; _fbp=fb.1.1674047835635.1752225705; _hjSessionUser_868286=eyJpZCI6ImJhZTdiMzhkLTI5YjUtNThkNy05MTBkLTdhYzI0ODJkNWZkYyIsImNyZWF0ZWQiOjE2NzQwNDc4MzcwMzAsImV4aXN0aW5nIjp0cnVlfQ==; SPC_CLIENTID=UklsNHBRN1VCc0V0prmgntzfdxakaggj; SC_DFP=LavZGLmkKaNSajRDXUVUDROAuSKcPlnV; _ga_3XVGTY3603=GS1.1.1674094914.1.1.1674098158.60.0.0; fulfillment-language=vi; _gcl_aw=GCL.1695619256.Cj0KCQjwvL-oBhCxARIsAHkOiu3r_LuCIsarOKiaDvBEFW6WsHIvuXsflEBXI8927biKe57vw3VHixMaAjfwEALw_wcB; _gac_UA-61914164-6=1.1695619257.Cj0KCQjwvL-oBhCxARIsAHkOiu3r_LuCIsarOKiaDvBEFW6WsHIvuXsflEBXI8927biKe57vw3VHixMaAjfwEALw_wcB; _med=refer; SPC_T_IV=RkhsWDk5bXo0dnZhaGx4aw==; SPC_R_T_ID=mp/cue0/wpgbyn3huagubZbEzsXmTxCHAEB6SxtFF1g45P8muPVHD9a89dHc2WKIMTs7z3w1plls076J+C28zVlOQTSRznZF9IfCrClaruglGAJ/d+dhEf8FJpZTLmsfQ2GJ1lQZWtiybwYkclXPB5iLVMcWWKgF93K5lc3QPZE=; SPC_R_T_IV=RkhsWDk5bXo0dnZhaGx4aw==; SPC_T_ID=mp/cue0/wpgbyn3huagubZbEzsXmTxCHAEB6SxtFF1g45P8muPVHD9a89dHc2WKIMTs7z3w1plls076J+C28zVlOQTSRznZF9IfCrClaruglGAJ/d+dhEf8FJpZTLmsfQ2GJ1lQZWtiybwYkclXPB5iLVMcWWKgF93K5lc3QPZE=; _gcl_au=1.1.219034057.1697794719; SPC_SI=ZIc3ZQAAAABhVnk5aDZyRMuSbwEAAAAAbTEzeVYyRnU=; _gid=GA1.2.1942530867.1699537413; _ga=GA1.1.1738283167.1674047837; SPC_ST=.ejBwS3FVWEhWUWp6Tkl2ax5hZ1m2vtTFAFT3+928cq+o9ABf5es8FjWGxc18VaBnYXfUm6XHyThvumSI6C+zaE0/FZp7f2knIwbD8/1kr+wWeXSk1wR3YXvU+vqXeB8anZHCXD8mgUldRsr68jw7pA1cPlScaZyBGunPBU41tiXDIUS9zlZDDSsX//irGxDVuVChqQkgXimDzOJ9sT1Qvg==; _ga_M32T05RVZT=GS1.1.1699537412.34.1.1699539443.60.0.0; SPC_SC_TK=51425caa4251ae15d8567f68dddc1b9d; SPC_SC_UD=113891819; SPC_STK=MMENR5NUGjeNeMLK7lsdC9z4zid/qFDgdWOv+cfdRqWSUyJDj0vE7mZE/GwJhkbo9vTXxLugk8v9wB+SkHEe8VQMQupxB2RXpx0yC0e9rzVeCCPoXnCu8+wfMKrqnn5WEB8UuBzDIOpxhTrl3JejmBXwNChagP9i+utS80nj/Qo08Nz1xcVAOz4npAp4ovbY; SPC_EC=d20wTm5VdFdRZnI4SnBxZPjZQObVc3LepAB7I4RuZLK2vAEJMqXSK/W49DQLzeHG2yjvhbeYEgWLKB9pbl0g641/um1XrGv+HroNWeXE1MR+5XbbILpQsZhF9UIMEgJ4/c6UUX2m87KvTTqAnkA1l7/oRwHifXizz4mOH2n7Bk0=; SPC_U=113891819; SPC_CDS=89821ebd-9f44-427e-9e2d-8fe888c1307d; CTOKEN=EkxW5X%2BKEe67x2b0ymsIJA%3D%3D; _QPWSDCXHZQA=e0fca7ee-a72e-4be3-efd2-a2c2b818d57b; REC7iLP4Q=0203d28b-223b-49be-bfe3-159830a2cb9e; shopee_webUnique_ccd=wHmqng1WKeivfYc6Gf1COw%3D%3D%7C9wx4%2BYI8lOwe9dCBBTnFy6sZ1NrT7ogIYzS067ywmBApQ0QYm0RwR1HjcjHcyuYw9eF9BH9JXYs%3D%7COoopPzmJwAHVsODI%7C08%7C3; ds=54d797fc2b04a264ad673d9e9f68eb6a");
    header.set("authority", "banhang.shopee.vn");
    String request = CreateArtShopeeRq.tuiDeoNuRequest;
    String formatedRequest = request.replace("$ARTICLE_NAME", "tui nu deo cheo nguc 2").replace("$ARTICLE_DESCRIPTION",
        "tui nu deo nguctui " + "nu deo nguctui nu deo nguctui nu deo nguctui nu deo nguctui nu"
            + " deo nguctui nu deo nguctui nu deo nguctui nu deo nguctui nu "
            + "deo nguctui nu deo nguctui nu deo nguctui nu deo nguctui nu deo nguctui "
            + "nu deo nguctui nu deo nguctui nu deo nguctui nu deo nguctui nu deo nguctui "
            + "nu deo nguctui nu deo nguctui nu deo nguctui nu deo nguctui nu deo nguctui nu deo nguc");
    ;
    System.out.println("request: " + formatedRequest);
    return new HttpEntity(formatedRequest, header);
  }
}
