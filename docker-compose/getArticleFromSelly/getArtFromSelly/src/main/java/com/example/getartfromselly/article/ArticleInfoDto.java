package com.example.getartfromselly.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfoDto implements Serializable {
    @JsonProperty("sellyPros")
    private List<SellyProduct> sellyPros;

}
