package com.example.getartfromselly.article.request;

import com.example.getartfromselly.dto.SellyLoginDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetArtWithKeyWordRequest {
    private String keyWord;
    private SellyLoginDto sellyLogin;
}
