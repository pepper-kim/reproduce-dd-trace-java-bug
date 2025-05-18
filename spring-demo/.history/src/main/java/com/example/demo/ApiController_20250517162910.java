package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pinned")
public class ApiController {

  @GetMapping("/test")
  public String getTestData() {

    Thread.ofVirtual();
    return "hello";
  }
}
