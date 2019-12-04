package com.offcn.demos;

import com.example.ReptileWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 侯叶飞
 * @date 2019/12/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReptileWebApplication.class)
public class Demo1 {

    @Autowired
    private LoadBalancerClient client;

    @Test
    public void demo1(){
        for (int i = 0; i < 10; i++) {
            ServiceInstance search = client.choose("search");

            System.out.println(search.getUri());
        }
    }
}
