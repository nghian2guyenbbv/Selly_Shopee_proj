package com.example.getartfromselly.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfo {


    @JsonIgnore
    public ArticleInfoDto toArtInfoDto() {
        List<Product> lisPro = this.getData().getProducts();
        List<SellyProduct> lisSellyPro = lisPro.stream().map(pr -> {
            SellyProduct sellyProduct = new SellyProduct();
            sellyProduct.setName(pr.getName());
            List<String> listPhotoUrl = pr.getPhotos().stream().map(Photo::getDimentions).map(dim -> Optional.ofNullable(dim).map(Dimention::getMd).orElse(null))
                    .map(md -> Optional.ofNullable(md).map(MD::getUrl).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList());
            sellyProduct.setListPhotoUrl(listPhotoUrl);
            return sellyProduct;
        }).collect(Collectors.toList());
        return ArticleInfoDto.builder().sellyPros(lisSellyPro).build();
    }

    @Data
    public class ProductData{
        @JsonProperty("products")
        private List<Product> products;
        private Price price;

        /*@Data
        public class Product{
            private String name;
            private List<Photo> photos;

            @Data
            public class Photo{
                private String name;
                @JsonProperty("dimensions")
                private Dimention dimentions;

                @Data
                public class Dimention{
                    private MD md;

                    @Data
                    public class MD{
                        private String url;
                    }
                }

            }

        }*/

        @Data
        public class Price{
            private long maximum;
            private long profit;
        }

    }




    @JsonProperty("action")
    private String action;
    @JsonProperty("data")
    private ProductData data;

}
