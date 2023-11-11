package com.dropship.uploadshoppeart.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetArtWithKeyWordRequest {
    private String keyWord;
    private SellyLoginDto sellyLogin;
}
