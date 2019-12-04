package com.example.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 侯叶飞
 * @date 2019/12/4
 */
@Data

public class Result implements Serializable {

    private  Integer status;

    private String message;

    private Object data;

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Result(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }
}
