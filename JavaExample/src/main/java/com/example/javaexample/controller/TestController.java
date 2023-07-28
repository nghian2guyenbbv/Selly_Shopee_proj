package com.example.javaexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @GetMapping("/get-customer")
  public Customer getCustomer() {
    return Customer.builder().name("nghia").age(23).build();
  }
}
