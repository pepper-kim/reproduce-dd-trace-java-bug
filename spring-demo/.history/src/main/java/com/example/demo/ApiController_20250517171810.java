package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/pinned")
public class ApiController {

  private final Lock lock = new ReentrantLock();

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @GetMapping("/test")
  public String getTestData() {
    Thread.ofVirtual().start(() -> {
        doPinned();
    });

    return "hello";
  }

  private void doPinned() {
    System.out.println("Im pin doPinned method!");
    lock.lock();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      lock.unlock();
      System.out.println("pinned ended!");
    }
  }
}
