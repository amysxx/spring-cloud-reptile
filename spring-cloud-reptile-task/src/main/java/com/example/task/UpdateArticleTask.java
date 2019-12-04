package com.example.task;

import com.example.domain.Article;
import com.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 侯叶飞
 * @date 2019/12/2
 */
@Component
public class UpdateArticleTask {

    @Autowired
    private ArticleService articleService;


    @Scheduled(cron = "0 0 0/2 * * ?")
    public void updateNewsTask(){

        List<Article> list = articleService.list();

        for (Article article : list) {
            articleService.add(article);
        }

    }
}
