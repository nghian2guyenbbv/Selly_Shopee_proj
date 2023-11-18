package com.dropship.uploadshoppeart.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateShopeeData {
  @JsonProperty("product_id")
  String productId;
}
