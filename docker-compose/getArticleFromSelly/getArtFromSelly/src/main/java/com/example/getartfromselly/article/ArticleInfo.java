package com.example.getartfromselly.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfo {

    private String actions;


    @JsonIgnore
    public ArticleInfoDto toArtInfoDto() {
        List<Product> lisPro = this.getData().getProducts();
        List<SellyProduct> listSellyPro = lisPro.stream().map(pr -> {
            SellyProduct sellyProduct = new SellyProduct();
            sellyProduct.setName(pr.getName());
            sellyProduct.setDescription(pr.getShareDesc());
            sellyProduct.setPrice(pr.getPrice().getMaximum());
            List<String> listPhotoUrl = pr.getPhotos().stream().map(Photo::getDimentions).map(dim -> Optional.ofNullable(dim).map(Dimention::getMd).orElse(null))
                    .map(md -> Optional.ofNullable(md).map(MD::getUrl).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList());
            sellyProduct.setListPhotoUrl(listPhotoUrl);
            return sellyProduct;
        }).collect(Collectors.toList());
        return ArticleInfoDto.builder().sellyPros(listSellyPro).build();


    }




    @JsonProperty("action")
    private String action;
    @JsonProperty("data")
    private ProductData data;

}
