package com.example.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 侯叶飞
 * @date 2019/12/4
 */
@Configuration

@Data
public class ImgProperties {

    @Value("${img.path}")
    private String path;

}
