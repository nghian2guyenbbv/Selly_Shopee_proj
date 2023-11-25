package com.example.getartfromselly.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Product {
    private String name;
    private List<Photo> photos;
    private String shareDesc;
    @JsonProperty("price")
    private Price price;

}
