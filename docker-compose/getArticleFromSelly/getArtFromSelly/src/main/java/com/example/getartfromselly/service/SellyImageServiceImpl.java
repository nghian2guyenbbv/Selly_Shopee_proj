package com.example.getartfromselly.service;

import com.example.getartfromselly.common.CommonClient;
import com.example.getartfromselly.common.ImageUtils;
import com.example.getartfromselly.common.ShopeeImageName;
import com.example.getartfromselly.repo.ProductPhotoUrlRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class SellyImageServiceImpl implements SellyImageService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ProductPhotoUrlRepo productPhotoUrlRepo;

  @Override
  public List<ShopeeImageName> downloadImagesWithUrl(String productName) {
    return null;
  }

  @Override
  public void saveShopeeImageName(List<ShopeeImageName> shopeeImageNames) {

  }

  @Override
  public void downloadImagesAndRename(String productName) {
    @NonNull List<String> originalUrls = productPhotoUrlRepo.getListPhotoUrlByProductName(productName);
    HttpHeaders headers = CommonClient.getDefaultHeader();
    headers.setAccept(Collections.singletonList(MediaType.IMAGE_JPEG));
    RequestCallback requestCallback = restTemplate.httpEntityCallback(null, byte[].class);
    ResponseExtractor<ResponseEntity<byte[]>> responseExtractor = restTemplate.responseEntityExtractor(byte[].class);
    int index = 0;
    for (String originalUrl : originalUrls) {
      ResponseEntity<byte[]> response = restTemplate.execute(originalUrl, HttpMethod.GET,requestCallback, responseExtractor, headers);
      System.out.println("------");
      if (response.getStatusCode().is2xxSuccessful()) {
        byte[] imageBytes = response.getBody();
        // Process the image bytes as needed
        ImageUtils.saveImage(imageBytes, productName+"_"+index);
      } else {
        // Handle error
        System.out.println("Error: " + response.getStatusCode());
      }
      index++;
    }
  }
}
