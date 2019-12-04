package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 侯叶飞
 * @date 2019/11/29
 */
@RestController
@RequestMapping(value = "/api/v1/web")
public class WebControllerV1 {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/find")
    public String find(){

        return restTemplate.getForObject("http://localhost:9001/api/search/findAll", String.class);
    }
}
