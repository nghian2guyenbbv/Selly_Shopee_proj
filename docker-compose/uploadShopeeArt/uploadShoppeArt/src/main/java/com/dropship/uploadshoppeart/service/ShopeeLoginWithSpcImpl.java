package com.dropship.uploadshoppeart.service;

import com.dropship.uploadshoppeart.common.CommonClient;
import com.dropship.uploadshoppeart.response.SPCInfo;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ShopeeLoginWithSpcImpl extends CommonClient implements ShopeeLoginWithSpc {
  @Value("${shopee.spc.info.url}")
  private String GET_SPC_INFO_URL;
  @Value("${shopee.spc.st}")
  private String spcSt;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public SPCInfo getSpcInfo() {

    HttpEntity<SPCInfo> spcInfoEntity = restTemplate.exchange(GET_SPC_INFO_URL, HttpMethod.GET,
        getShopeeGetSpcInfoEntity(), SPCInfo.class);
    System.out.println("spcInfoEntity: " + spcInfoEntity.getBody().toString());
    return spcInfoEntity.getBody();
  }

  @Override
  public String getSpcToken() {
    SPCInfo spcInfo = getSpcInfo();
    if (Optional.ofNullable(spcInfo).isPresent()) {
      return spcInfo.getSpcToken();
    }
    throw new ServiceException("Error get spc Token");
  }

  private HttpEntity getShopeeGetSpcInfoEntity() {
    HttpHeaders header = getDefaultHeader();
    header.set("cookie", spcSt);
    return new HttpEntity(header);
  }
}
