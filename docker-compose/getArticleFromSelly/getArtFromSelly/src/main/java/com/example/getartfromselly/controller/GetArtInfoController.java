package com.example.getartfromselly.controller;

import com.example.getartfromselly.article.ArticleInfoDto;
import com.example.getartfromselly.article.request.GetArtWithKeyWordRequest;
import com.example.getartfromselly.entity.ArticleSellyDto;
import com.example.getartfromselly.entity.ProductPhotoUrlDto;
import com.example.getartfromselly.repo.ArticleSellyRepository;
import com.example.getartfromselly.service.GetArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private GetArticleInfoService getArticleInfoService;

    @Autowired
    private ArticleSellyRepository articleSellyRepository;


    @PostMapping("/getArtInfo")
    public ArticleInfoDto getArticleInfoWithKeyWord(@RequestBody GetArtWithKeyWordRequest getArtWithKeyWordRequest) {
        ArticleInfoDto artInfo = getArticleInfoService.getArticleInfoFromSelly(getArtWithKeyWordRequest).orElse(null);
        List<ProductPhotoUrlDto> listProductPhoto = new ArrayList<ProductPhotoUrlDto>();
        AtomicReference<String> description = new AtomicReference<>(" ");
        AtomicReference<String> productName = new AtomicReference<>(" ");

        artInfo.getSellyPros().forEach(pro-> {
            productName.set(pro.getName());
            pro.getListPhotoUrl().forEach(photo -> {
                ProductPhotoUrlDto proDto = ProductPhotoUrlDto.builder()
                    .productId(1234).photoUrl(photo)
                    .build();
                listProductPhoto.add(proDto);
                description.set(pro.getDescription());
            });
            ArticleSellyDto articleSellyDto = ArticleSellyDto.builder()
                .productName(productName.get()).description(description.get()).productUrl(listProductPhoto).articleType(getArtWithKeyWordRequest.getKeyWord()).build();
            articleSellyRepository.save(articleSellyDto);
        });
        return artInfo;
    }
}
