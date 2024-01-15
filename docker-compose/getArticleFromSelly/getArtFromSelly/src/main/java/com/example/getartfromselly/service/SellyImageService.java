package com.example.getartfromselly.service;

import com.example.getartfromselly.common.ShopeeImageName;

import java.util.List;

public interface SellyImageService {
  List<ShopeeImageName> downloadImagesWithUrl(String productName);
  void saveShopeeImageName(List<ShopeeImageName> shopeeImageNames);

  void downloadImagesAndRename(String productName);
}
