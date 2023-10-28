package com.example.getartfromselly.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ARTICLE_SELLY")
public class ArticleSellyDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_selly_id;
    @Column(name="PRODUCT_NAME")
    private String productName;
    @Column(name="DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "articleSelly")
    private List<ProductPhotoUrlDto> productUrl;
}
