package com.example.getartfromselly.entity;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT_PHOTO_URL")
public class ProductPhotoUrlDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_photo_id;
    @Column(name="PRODUCT_ID")
    private int productId;
    @Column(name="PHOTO_URL")
    private String photoUrl;
    @ManyToOne
    @JoinColumn(name = "product_selly_id")
    private ArticleSellyDto articleSelly;
}
