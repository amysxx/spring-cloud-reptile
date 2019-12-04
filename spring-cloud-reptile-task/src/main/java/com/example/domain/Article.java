package com.example.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author 侯叶飞
 * @date 2019/12/3
 */
@Data
@Table(name = "t_article")
public class Article implements Serializable {
    /**
     * 新闻的ID
     */
    @Id
    private String id;

    /**
     * 简介
     */
    private String intro;
    /**
     * 关键词
     */
    private String keywords;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String imgs;

    /**
     * 文章内容
     */
    private String content;

}
