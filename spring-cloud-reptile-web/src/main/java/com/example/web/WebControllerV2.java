package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author 侯叶飞
 * @date 2019/11/29
 */
@RestController
@RequestMapping(value = "/api/v2/web")
public class WebControllerV2 {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/find")
    public String find(){

        // 根据服务名称，获取服务实例
        List<ServiceInstance> instances = client.getInstances("search");
        // TODO 因为只有一个SEARCH,因此我们直接get(0)获取
        ServiceInstance instance = instances.get(0);
        //// 获取ip和端口信息
        ////http://host.docker.internal:9001
        return restTemplate.getForObject("http://"+instance.getHost()+":"+instance.getPort()+"/api/search/findAll", String.class);
    }
}
