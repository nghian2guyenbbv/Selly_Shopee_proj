package com.example.getartfromselly.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductData {
  @JsonProperty("products")
  private List<Product> products;
/*
  private Price price;
*/

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

}
