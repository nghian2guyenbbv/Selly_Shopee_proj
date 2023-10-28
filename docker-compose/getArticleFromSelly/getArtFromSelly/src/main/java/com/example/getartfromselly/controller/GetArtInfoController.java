package com.example.getartfromselly.controller;

import com.example.getartfromselly.article.ArticleInfoDto;
import com.example.getartfromselly.article.request.GetArtWithKeyWordRequest;
import com.example.getartfromselly.entity.ArticleSellyDto;
import com.example.getartfromselly.repo.ArticleSellyRepository;
import com.example.getartfromselly.service.GetArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        ArticleSellyDto articleSellyDto = new ArticleSellyDto();
        articleSellyRepository.save(articleSellyDto);
        return artInfo;
    }
}
