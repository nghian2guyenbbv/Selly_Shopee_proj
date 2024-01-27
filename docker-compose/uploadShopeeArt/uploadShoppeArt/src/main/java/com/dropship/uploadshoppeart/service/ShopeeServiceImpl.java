package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.common.ClientIdentity;
import com.dropship.uploadshoppeart.common.CommonClient;
import com.dropship.uploadshoppeart.common.LoginByPassWordPayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ShopeeServiceImpl extends CommonClient implements ShopeeService {
  @Autowired
  private RestTemplate restTemplate;
  @Value("${shopee.url.login-by-password}")
  private String LOGIN_BY_PASSWORD;

  @Value("${shopee.url.login-spc-cds}")
  private String LOGIN_SPC_CDS;

  @Override
  public List<String> getCookieFromLoginSPC() {
    ResponseEntity loginByPasswordResponse = restTemplate.exchange(LOGIN_BY_PASSWORD, HttpMethod.POST,
        getLoginByPassWordEntity(), String.class);
    ResponseEntity loginBySPCResponse = restTemplate.exchange(LOGIN_SPC_CDS, HttpMethod.GET,
        getLoginSPCEntity(getSetCookieResponse(loginByPasswordResponse)), String.class);
    List<String> baseCookie = getSetCookieResponse(loginBySPCResponse);
    System.out.println("baseCookie: " + baseCookie.toString());
    return baseCookie;
  }

  private HttpEntity getLoginByPassWordEntity() {
    HttpHeaders header = getDefaultHeader();
    header.set("authority", "shopee.vn");
    header.set("origin", "https://shopee.vn");
    header.set("referer", "https://shopee.vn/seller/login");
    header.set("x-api-source", "pc");
    header.set("x-requested-with", "XMLHttpRequest");
    header.set("x-shopee-language", "vi");
    header.set("x-sz-sdk-version", "3.4.0-2&1.6.13");
    header.set("user-agent",
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
    LoginByPassWordPayLoad request = new LoginByPassWordPayLoad().builder().clientIden(new ClientIdentity(
            "fYhJRynMXc+bgeYwuGhwcw==|EHF0ZrJTMvdwbkrUjfCLvtakpH79DRb6BJ6QfIQZh640w/bucCu6o5pIIS2nSQMaUvmMHyt7EgYyLA==|WGvsy5UJbJkbYInM|08|3"))
        .phone("84586099640").password("951cc1a85ee5318b0524bde361918cf3596ee5018fa8b670d9c15e6d1fa2d6bb")
        .support_ivs(true).build();
    return new HttpEntity(request, header);
  }

  public HttpHeaders getDefaultHeader() {
    // Default header
    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return httpHeaders;
  }

  public HttpEntity getLoginSPCEntity(List<String> baseCookie) {
    HttpHeaders header = getDefaultHeader();
    header.set(HttpHeaders.COOKIE, StringUtils.arrayToDelimitedString(baseCookie.toArray(), ";"));
    header.set("referer", "https://banhang.shopee.vn/?is_from_login=true");
    header.set("authority", "banhang.shopee.vn");
    return new HttpEntity<>(header);
  }
}
