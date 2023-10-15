package com.example.getartfromselly.service;

import com.example.getartfromselly.dto.SellyLoginDto;
import com.example.getartfromselly.login.token.Token;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetTokenSeviceImpl implements GetSellyTokenService {
    public static final String GET_CURRENT_TOKEN_URL = "http://localhost:8080/sellyAuthenticate/getCurrentToken";
    public static final String REFRESH_TOKEN_URL = "http://localhost:8080/sellyAuthenticate/refreshToken";
    // Default header
    final HttpHeaders httpHeaders = new HttpHeaders();

    private HttpHeaders getDefaultHeader() {
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class SellyUserLogin {
        @JsonProperty("userName")
        String userName;
        @JsonProperty("passWord")
        String passWord;
    }

    private HttpEntity getLoginEntity(SellyUserLogin sellyUser) {
        SellyLoginDto body = SellyLoginDto.builder().userName(sellyUser.getUserName()).passWord(sellyUser.getPassWord()).build();
        return new HttpEntity(body, httpHeaders);

    }

    @Autowired
    private RestTemplate restTemplate;

    private SellyUserLogin getUserLogin (){
        return new SellyUserLogin("+84586099640", "123456");
    }

    @Override
    public Token getCurrentToken() {
        ResponseEntity<Token> currentToken = restTemplate.exchange(GET_CURRENT_TOKEN_URL, HttpMethod.POST, getLoginEntity(getUserLogin()), Token.class);
        return currentToken.getBody();
    }

    @Override
    public Token refreshToken() {
        ResponseEntity<Token> rereshedToken = restTemplate.exchange(REFRESH_TOKEN_URL, HttpMethod.POST, getLoginEntity(getUserLogin()), Token.class);
        System.out.println("rereshedToken.getBody(): "+rereshedToken.getBody());
        return rereshedToken.getBody();
    }
}
