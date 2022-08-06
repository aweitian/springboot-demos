package me.taw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/discovery")
public class DiscoveryController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/select")
    public String selectService() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://product/config/student", String.class);
        return responseEntity.getBody();
    }

}
