package com.example.service;

import com.example.domain.Article;

import java.util.List;

/**
 * @author 侯叶飞
 * @date 2019/12/3
 */
public interface ArticleService {


    List<Article> list();

    int add(Article article);
}
