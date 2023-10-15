package com.example.getartfromselly.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Photo {

    private String name;
    @JsonProperty("dimensions")
    private Dimention dimentions;

}
