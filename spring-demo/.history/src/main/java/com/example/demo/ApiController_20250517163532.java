package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pinned")
public class ApiController {

  @GetMapping("/test")
  public String getTestData() {

    Thread.ofVirtual().start(() -> {
        doPinned();
    });

    return "hello";
  }

  private void doPinned() {
    System.out.println("Im pin doPinned method!");
    synchronized (this) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } finally {
        System.out.println("pinned ended");
      }
    }
  }
}
