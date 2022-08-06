package me.taw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hunter")
public class HunterController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/puma")
    public String selectService() {
        //使用discovery-server 需要在 RestTemplateConfig 上加注解 @LoadBalanced
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://discovery-server/discovery/find", String.class);
        return responseEntity.getBody();
    }

}
