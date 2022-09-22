package me.taw.elk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class uu {


    @GetMapping("/hi")
    public String hello(@RequestParam("q") String q) {
        //manage space
        //index pattern
        //discovery
        log.info("输出info" +q);
        log.debug("输出debug");
        log.error("输出error");
        return "elk";
    }
}
