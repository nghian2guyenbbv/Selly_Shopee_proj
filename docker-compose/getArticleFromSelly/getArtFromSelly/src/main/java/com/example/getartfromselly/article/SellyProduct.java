package com.example.getartfromselly.article;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SellyProduct {
    private String name;
    private List<String> listPhotoUrl;
}
