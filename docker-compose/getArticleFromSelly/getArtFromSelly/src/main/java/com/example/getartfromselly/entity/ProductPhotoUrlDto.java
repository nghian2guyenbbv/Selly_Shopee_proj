package com.example.getartfromselly.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.persistence.*;

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
}
