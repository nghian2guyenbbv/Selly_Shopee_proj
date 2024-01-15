package com.example.getartfromselly.repo;

import com.example.getartfromselly.entity.ProductPhotoUrlDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface ProductPhotoUrlRepo extends JpaRepository<ProductPhotoUrlDto, Long> {
  public List<ProductPhotoUrlDto> findProductPhotoUrlDtosByProductName(String productName);

  default List<String> getListPhotoUrlByProductName(String productName) {
    List<ProductPhotoUrlDto> products = findProductPhotoUrlDtosByProductName(productName);
    return products.stream().filter(pr -> StringUtils.isNotEmpty(pr.getPhotoUrl())).map(ProductPhotoUrlDto::getPhotoUrl)
        .collect(Collectors.toList());
  }
}
