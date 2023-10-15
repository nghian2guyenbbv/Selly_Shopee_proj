package com.example.getartfromselly.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SellyLoginDto {
    private String userName;
    private String passWord;
}
