package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "demo", url = "http://192.168.0.19:4041")
public interface svc {
    @GetMapping("/add")
    String testGet();

    @GetMapping("/sub")
    String ddd();
}
