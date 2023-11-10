package com.dropship.uploadshoppeart.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppeArticle {
  @JsonProperty(namespace = "artName")
  String artName;
  @JsonProperty(namespace = "artPrice")
  double price;
  @JsonProperty(namespace = "artAddress")
  String address;

  @JsonProperty(namespace = "description")
  private String description;
  @JsonIgnore
  double profit;
}
