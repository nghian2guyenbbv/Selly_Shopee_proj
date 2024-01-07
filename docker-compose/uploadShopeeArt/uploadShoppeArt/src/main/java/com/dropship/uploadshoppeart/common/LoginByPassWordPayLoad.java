package com.dropship.uploadshoppeart.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginByPassWordPayLoad {
  private String phone;
  private String password;
  private boolean support_ivs;
  @JsonProperty("client_identifier")
  private ClientIdentity clientIden;

}
