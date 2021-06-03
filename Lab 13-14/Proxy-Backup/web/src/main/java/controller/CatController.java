package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api")
public class CatController {
    private final String catUrl = "http://cats:8080/api";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/cats")
    Object getCats() {
        System.out.println("OOOOOH we're halfway there");
        return restTemplate.getForObject(catUrl + "/cats", Object.class);
    }
}
