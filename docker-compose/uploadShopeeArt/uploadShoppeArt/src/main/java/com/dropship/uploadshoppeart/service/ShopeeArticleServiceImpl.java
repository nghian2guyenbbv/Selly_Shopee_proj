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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopeeArticleServiceImpl extends CommonClient implements ShopeeArticleService {
  @Value("${shopee.url.createArt}")
  private String CREATE_SHOPPE_ART_URL;

  @Value("${shopee.cookie}")
  private String SHOPEE_COOKIE;

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
    header.set("cookie", SHOPEE_COOKIE);
    //header.set("cookie", "SPC_F=RIl4pQ7UBsEtWaey2oOtSKyIiI6FrSdg; REC_T_ID=6b00daf9-9732-11ed-8ad5-3473791746da; _fbp=fb.1.1674047835635.1752225705; _hjSessionUser_868286=eyJpZCI6ImJhZTdiMzhkLTI5YjUtNThkNy05MTBkLTdhYzI0ODJkNWZkYyIsImNyZWF0ZWQiOjE2NzQwNDc4MzcwMzAsImV4aXN0aW5nIjp0cnVlfQ==; SPC_CLIENTID=UklsNHBRN1VCc0V0prmgntzfdxakaggj; SC_DFP=LavZGLmkKaNSajRDXUVUDROAuSKcPlnV; _ga_3XVGTY3603=GS1.1.1674094914.1.1.1674098158.60.0.0; fulfillment-language=vi; _gcl_au=1.1.219034057.1697794719; SPC_U=-; _gcl_aw=GCL.1702536111.Cj0KCQiAyeWrBhDDARIsAGP1mWT-uzggzMlv7GFGg6wFDMXiIc_lDwW_WQNnmtqYSOABJEX2nyIlZYwaAtNoEALw_wcB; SPC_T_ID=9NnUSk2WizBj60TfIV+voMfnsUggaaYB5oFZokwoScm4LmgHjQIkisnzHk8BH0DLdlDzJt+/Us6JRrhdnebCtnac/ioYEenui+nqVUO5zKxiSVb/djs/RDK/X2lFmH3KmNqha7bsM+lv4paKiFYRiqnPR6qDkDXMr4CoPg5p6kc=; SPC_T_IV=b1JEbGZLOXlMOUtiOXpuSw==; SPC_R_T_ID=9NnUSk2WizBj60TfIV+voMfnsUggaaYB5oFZokwoScm4LmgHjQIkisnzHk8BH0DLdlDzJt+/Us6JRrhdnebCtnac/ioYEenui+nqVUO5zKxiSVb/djs/RDK/X2lFmH3KmNqha7bsM+lv4paKiFYRiqnPR6qDkDXMr4CoPg5p6kc=; SPC_R_T_IV=b1JEbGZLOXlMOUtiOXpuSw==; _gac_UA-61914164-6=1.1702536115.Cj0KCQiAyeWrBhDDARIsAGP1mWT-uzggzMlv7GFGg6wFDMXiIc_lDwW_WQNnmtqYSOABJEX2nyIlZYwaAtNoEALw_wcB; _ga_M32T05RVZT=GS1.1.1702536114.42.1.1702536136.38.0.0; _med=refer; SPC_SI=s2+BZQAAAABnWm5kcVNicYkgfAAAAAAAc3BHaFdBdkU=; AMP_TOKEN=%24NOT_FOUND; _gid=GA1.2.1608063269.1703498857; SPC_CDS=96cb44b6-3307-4f1f-807c-46bdacbfb95a; _ga=GA1.2.1738283167.1674047837; SPC_EC=.SDNuRlBOUUg3NDVZRHpieZyvpE/VP721wZqeoxa1b5HuyZPE9QDqnu73kTDtyDYKkmGLyIj0rMtj2NXt+DG2SkYlQQEvH8Hr01+i88zr2ONuysl6sfk0d8VT85Sn1HqrL8Em4VsISqmTY6VLeFXISj6z82RlK8hKjb+fYtQPUIF66RRyFpHMc0Bs0Ibx8Ulv160M+cZJcWkvYW7MqYMrnA==; SPC_ST=.SDNuRlBOUUg3NDVZRHpieZyvpE/VP721wZqeoxa1b5HuyZPE9QDqnu73kTDtyDYKkmGLyIj0rMtj2NXt+DG2SkYlQQEvH8Hr01+i88zr2ONuysl6sfk0d8VT85Sn1HqrL8Em4VsISqmTY6VLeFXISj6z82RlK8hKjb+fYtQPUIF66RRyFpHMc0Bs0Ibx8Ulv160M+cZJcWkvYW7MqYMrnA==; _ga_4GPP1ZXG63=GS1.1.1703498855.1.1.1703499083.0.0.0; SPC_SC_TK=cbed50795bd0274f33b20f2675df73e5; SPC_SC_UD=113891819; SPC_STK=bblUtRy5Xo8R2LJhgxj2VaPIMlBJtiHeqPSuTBsYgsF5G31+lLz0s1tviohQwKmlLTMUIFLMQjnFZt/FMiDtHZKucInNfSLC+z5l388gp8oEA0wuAikLRGFri3pBP9KwUBvqzKMh6F4/Px7Sg0BBDyAyKf37VfAtA9m4SlgIKtlI5ylj6bXbqrixI15PYOt7; _QPWSDCXHZQA=e0fca7ee-a72e-4be3-efd2-a2c2b818d57b; shopee_webUnique_ccd=lQtdFdhdsdylE%2FcxQMVmvw%3D%3D%7Cduo0pXITD60elHI%2B5F7DZa30LkOHJwmnoKmmg30t%2BAlaCO9zbSH5r4K0PmScNgHuUG3qkaKnFOI%3D%7CzicfMecv6xuRzZla%7C08%7C3; CTOKEN=8%2BQL36MOEe6kh3qz8Mtfmw%3D%3D");
    String request = CreateArtShopeeRq.tuiDeoNuRequest;
    String formatedRequest = request.replace("$ARTICLE_NAME", shopeeArticle.getArtName())
        .replace("$ARTICLE_DESCRIPTION", FormatmessageShopeeRule.formatMessage(shopeeArticle.getDescription()));
    ;
    System.out.println("request: " + formatedRequest);
    return new HttpEntity(formatedRequest, header);
  }
}
