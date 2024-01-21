package com.example.getartfromselly.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_PHOTO_URL")
public class ProductPhotoUrlDto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "PRODUCT_ID")
  private int productId;
  @Column(name = "PRODUCT_NAME")
  private String productName;
  @Column(name = "PHOTO_URL")
  private String photoUrl;
  @Column(name = "SHOPEE_PHOTO_ID")
  private String shopeePhotoId;
}
