package com.dropship.uploadshoppeart.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientIdentity {
  private String security_device_fingerprint;
}
