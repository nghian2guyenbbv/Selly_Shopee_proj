package com.example.getartfromselly.repo;

import com.example.getartfromselly.entity.ProductPhotoUrlDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPhotoUrlRepository extends JpaRepository<ProductPhotoUrlDto, Integer> {
}
