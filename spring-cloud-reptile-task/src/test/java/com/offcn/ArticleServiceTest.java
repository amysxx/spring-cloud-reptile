package com.offcn;

import com.alibaba.fastjson.JSON;
import com.example.TaskApplication;
import com.example.domain.Article;
import com.example.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author 侯叶飞
 * @date 2019/12/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskApplication.class)
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void demo1() throws IOException {

        List<Article> list = articleService.list();

        System.out.println(list);
    }

    @Test
    public void demo2(){


        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");

        //[aa, bb, cc, dd]
        String str = Arrays.toString(list.toArray());

        System.out.println(str.substring(1,str.length()-1));

    }

    @Test
    public void demo3(){

        List<Article> list = articleService.list();

        for (Article article : list) {
            articleService.add(article);
        }
        System.out.println ("添加成功" );

    }

    @Test
    public void demo4(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        Set<String> keys = redisTemplate.keys("article:*");

        keys.forEach(key->{

            String value = ops.get(key);

            Article article = JSON.parseObject(value, Article.class);
            articleService.add(article);
        });

    }
}
