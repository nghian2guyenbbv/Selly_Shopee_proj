package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.article.ShoppeArticle;
import com.dropship.uploadshoppeart.common.ClientIdentity;
import com.dropship.uploadshoppeart.common.CommonClient;
import com.dropship.uploadshoppeart.common.LoginByPassWordPayLoad;
import com.dropship.uploadshoppeart.dto.ArticleSellyDto;
import com.dropship.uploadshoppeart.repo.ArticleSellyRepo;
import com.dropship.uploadshoppeart.request.CreateArtShopeeRq;
import com.dropship.uploadshoppeart.response.CreateShopeeArtResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopeeArticleServiceImpl extends CommonClient implements ShopeeArticleService {
  @Value("${shopee.url.create-article}")
  private String CREATE_SHOPPE_ART_URL;

  @Value("${shopee.url.login-by-password}")
  private String LOGIN_BY_PASSWORD;

  @Value("${shopee.url.login-spc-cds}")
  private String LOGIN_SPC_CDS;

  @Autowired
  private ShopeeLoginWithSpc shopeeLoginWithSpc;

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
        HttpMethod.POST, getCreateArtEntity(shopeeArticle, getCookieFromLoginSPC()), CreateShopeeArtResponse.class);
    if (createShopeeArtRp.getBody().getCode() != 0) {
      System.out.println("ERROR: " + createShopeeArtRp.getBody().getMsg());
    }
    return null;
  }

  public List<String> getCookieFromLoginSPC() {
    ResponseEntity loginByPasswordResponse = restTemplate.exchange(LOGIN_BY_PASSWORD, HttpMethod.POST,
        getLoginByPassWordEntity(), String.class);
    ResponseEntity loginBySPCResponse = restTemplate.exchange(LOGIN_SPC_CDS, HttpMethod.GET,
        getLoginSPCEntity(getSetCookieResponse(loginByPasswordResponse)), String.class);
    List<String> baseCookie = getSetCookieResponse(loginBySPCResponse);
    System.out.println("baseCookie: " + baseCookie.toString());
    return baseCookie;
  }

  public HttpEntity getLoginByPassWordEntity() {
    HttpHeaders header = getDefaultHeader();
    header.set("authority", "shopee.vn");
    header.set("origin", "https://shopee.vn");
    header.set("referer", "https://shopee.vn/seller/login");
    header.set("x-api-source", "pc");
    header.set("x-requested-with", "XMLHttpRequest");
    header.set("x-shopee-language", "vi");
    header.set("x-sz-sdk-version", "3.4.0-2&1.6.13");
    header.set("user-agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
    LoginByPassWordPayLoad request = new LoginByPassWordPayLoad().builder().clientIden(new ClientIdentity(
            "fYhJRynMXc+bgeYwuGhwcw==|EHF0ZrJTMvdwbkrUjfCLvtakpH79DRb6BJ6QfIQZh640w/bucCu6o5pIIS2nSQMaUvmMHyt7EgYyLA==|WGvsy5UJbJkbYInM|08|3"))
        .phone("84586099640").password("951cc1a85ee5318b0524bde361918cf3596ee5018fa8b670d9c15e6d1fa2d6bb")
        .support_ivs(true).build();
    return new HttpEntity(request, header);
  }

  private HttpEntity getCreateArtEntity(ShoppeArticle shopeeArticle, List<String> baseCookie) {
    Assert.notNull(baseCookie, "base cookie must not be null");
    HttpHeaders header = getDefaultHeader();
    header.set(COOKIE_PARAM, baseCookie.toString());
    String request = CreateArtShopeeRq.tuiDeoNuRequest;
    String formatedRequest = request.replace("$ARTICLE_NAME", shopeeArticle.getArtName())
        .replace("$ARTICLE_DESCRIPTION", FormatmessageShopeeRule.formatMessage(shopeeArticle.getDescription()));
    ;
    System.out.println("request: " + formatedRequest);
    return new HttpEntity(formatedRequest, header);
  }

  public HttpEntity getLoginSPCEntity(List<String> baseCookie) {
    HttpHeaders header = getDefaultHeader();
    String ck = "REC_T_ID=e58e652b-ad24-11ee-8a90-baf7542d6a8b; SC_DFP=jhEvdTgxqcwLrZrCYoVAESytAvhtiANU; SPC_CLIENTID=bHpjVElrT2ozNmNswxaopnwpqscqttvn; SPC_EC=.a24zYlpraHRuSDRuSjFKONTOk2m2KelO71eLGJ6sT5xxAti2uYjZE6aVyNU7/stUWxe8kdvOiPalZTm0xb9S/3BUs+rCERXT9DIiFWEgCSAO3B4ferVuyUqEC3Q9wQD1j9IehuXnsUEdgrS3vDDpGzVB5B6J0mZwuf3cI9TWpLOFl8QoOArng0j6qNO5VAGdN+pzvRGqG0SrI8IAQEBC2Q==; SPC_F=lzcTIkOj36clNNPWTA0i02OHJJuyTLzC; SPC_R_T_ID=yxPikdxk8NBQxzjpHT+GmX1e02bueYSfVZ2KL+q1Hd0CPSeLQBhEKQ1bu9AdKvaLTsIecuOKFiHK7l+dCQ+lCdIWUT5f5VAMUr2dmdhhHwwF7riLEtgIs5XGE/kAy2KZTtgnhu+hxp5MJESq0nte3RUJQbQG3SDtzd7tNHZ1MRo=; SPC_R_T_IV=cTlSY2ZVajlkcWMwcVlmdA==; SPC_SC_TK=e28d1226c04e0e88e15b0b8fe05663cc; SPC_SC_UD=113891819; SPC_SI=1MqTZQAAAABMOTlaMnB3Wg8SVgAAAAAAdUd2VTdCU08=; SPC_ST=.a24zYlpraHRuSDRuSjFKONTOk2m2KelO71eLGJ6sT5xxAti2uYjZE6aVyNU7/stUWxe8kdvOiPalZTm0xb9S/3BUs+rCERXT9DIiFWEgCSAO3B4ferVuyUqEC3Q9wQD1j9IehuXnsUEdgrS3vDDpGzVB5B6J0mZwuf3cI9TWpLOFl8QoOArng0j6qNO5VAGdN+pzvRGqG0SrI8IAQEBC2Q==; SPC_STK=gqt28ULNKYQw5qNakoM/z0JcQEc11X5k9eEPEivt2asVX7yaHEnPcUS/WBgJsvtUzFZ2VH1S+bsaEdsSTqi8xskmY4/Wg4U3u2CuIX2+hCgfWflBadK3t2PvAohFjT7rMjkxdBvXLN5GawLp7z3tnVUfQQoA7yLsnOq81sAXiCG+WsqVD3flnPPGm5PUDeWU; SPC_T_ID=yxPikdxk8NBQxzjpHT+GmX1e02bueYSfVZ2KL+q1Hd0CPSeLQBhEKQ1bu9AdKvaLTsIecuOKFiHK7l+dCQ+lCdIWUT5f5VAMUr2dmdhhHwwF7riLEtgIs5XGE/kAy2KZTtgnhu+hxp5MJESq0nte3RUJQbQG3SDtzd7tNHZ1MRo=; SPC_T_IV=cTlSY2ZVajlkcWMwcVlmdA==";
    //header.set(HttpHeaders.COOKIE, CollectionUtils.isEmpty(baseCookie) ? Strings.EMPTY : baseCookie.toString());
        header.set(HttpHeaders.COOKIE, StringUtils.arrayToDelimitedString(baseCookie.toArray(), ";"));
    header.set("referer", "https://banhang.shopee.vn/?is_from_login=true");
    header.set("authority", "banhang.shopee.vn");
    return new HttpEntity<>(header);
  }
}
