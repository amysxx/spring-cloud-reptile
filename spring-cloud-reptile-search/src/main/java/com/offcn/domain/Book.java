package com.offcn.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 侯叶飞
 * @date 2019/11/29
 */
@Data
@Table
public class Book implements Serializable {

    @Id
    private Integer id;

    private String name;

    private Double price;

    private String description;

    private String img;
}