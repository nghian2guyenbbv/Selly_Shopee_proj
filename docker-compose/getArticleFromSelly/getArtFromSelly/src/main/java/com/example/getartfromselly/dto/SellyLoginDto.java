package com.example.getartfromselly.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SellyLoginDto {
    private String userName;
    private String passWord;
}
