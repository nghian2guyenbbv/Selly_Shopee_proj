package com.example.getartfromselly.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ARTICLE_SELLY")
public class ArticleSellyDto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int product_selly_id;
  @Column(name = "PRODUCT_NAME")
  private String productName;
  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "ARTICLE_TYPE")
  private String articleType;
  @Column(name = "ARTICLE_PRICE")
  private Double articlePrice;
}
