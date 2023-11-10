package com.dropship.uploadshoppeart.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShoppeArticle {
  @JsonProperty(namespace = "artName")
  String artName;
  @JsonProperty(namespace = "artPrice")
  double price;
  @JsonProperty(namespace = "artAddress")
  String address;
  @JsonIgnore
  double profit;
}
