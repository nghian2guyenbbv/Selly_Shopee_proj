package com.example.getartfromselly.common;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ShopeeImageName {
  private String productName;
  private List<String> imageNames;
}
