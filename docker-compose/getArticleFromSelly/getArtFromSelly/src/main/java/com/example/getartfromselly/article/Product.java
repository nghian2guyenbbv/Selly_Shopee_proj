package com.example.getartfromselly.article;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private String name;
    private List<Photo> photos;

}
