package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class uu {

    @GetMapping("/add")
    public String kd() {
        return "taw";
    }


    @GetMapping("/sub")
    public String kdx() {
        return "taw_sub";
    }
}
