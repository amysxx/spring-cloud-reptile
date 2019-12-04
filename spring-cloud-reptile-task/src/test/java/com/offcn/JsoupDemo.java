package com.offcn;

import com.example.TaskApplication;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.awt.geom.RectangularShape;

/**
 * @author 侯叶飞
 * @date 2019/12/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskApplication.class)
public class JsoupDemo {

    @Autowired
    private RestTemplate template;


    @Test
    public void demo1(){
        //发送一个Get请求，获取网页数据
        String result = template.getForObject("https://list.jd.com/list.html?cat=9987,653,655", String.class);
        //将获取的数据交给Jsoup进行解析，获取Document对象
        Document document = Jsoup.parse(result);

        Elements elements = document.getElementsByClass("gl-warp");

        //document.getElementById();
        //document.select("#xxx");



     ;

        System.out.println(template.getForObject("https://www.biduo.cc/",  String.class));


    }
}
