package com.dropship.uploadshoppeart.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SPCInfo {
  @JsonProperty("token")
  private String spcToken;
  @JsonProperty("shopid")
  private int shoppId;
}
