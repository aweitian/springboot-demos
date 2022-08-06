package me.taw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discovery")
public class DiscoveryController {
    @GetMapping("/find")
    public String selectService() {
        return "A puma at large @ V1.0";
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://product/config/student", String.class);
//        return responseEntity.getBody();
    }

}
