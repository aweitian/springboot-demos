package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class uu {

    @Autowired
    private svc svc;

    @GetMapping("/hi")
    public String hello() {
        return svc.testGet() + svc.ddd();
    }
}
