package com.example.getartfromselly.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Token {
  @JsonProperty("user")
  private String user;
  @JsonProperty("token")
  private String token;
}
