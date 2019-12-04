package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 侯叶飞
 * @date 2019/11/29
 */
@RestController
@RequestMapping(value = "/api/v3/web")
public class WebControllerV3 {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/find")
    public String find(){

        return restTemplate.getForObject("http://search/api/search/findAll", String.class);
    }
}
