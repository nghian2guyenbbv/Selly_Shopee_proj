package com.dropship.uploadshoppeart.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateShopeeArtResponse {
  int code;
  String msg;
  @JsonProperty("user_message")
  private String userMessage;
  @JsonProperty("data")
  private CreateShopeeData createShopeeData;

}
