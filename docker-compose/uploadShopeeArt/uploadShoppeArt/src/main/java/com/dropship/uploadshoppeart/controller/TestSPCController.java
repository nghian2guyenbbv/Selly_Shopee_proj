package com.dropship.uploadshoppeart.controller;

import com.dropship.uploadshoppeart.response.SPCInfo;
import com.dropship.uploadshoppeart.service.ShopeeLoginWithSpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spc")
public class TestSPCController {
  @Autowired
  private ShopeeLoginWithSpc shopeeLoginWithSpc;

  @GetMapping("/info")
  public SPCInfo getSpcInfo() {
    SPCInfo info = shopeeLoginWithSpc.getSpcInfo();
    return info;

  }

}
