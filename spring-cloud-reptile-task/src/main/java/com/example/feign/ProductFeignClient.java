package com.example.feign;


import com.example.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 声明需要调用的微服务名称
 *  @FeignClient
 *      * name : 服务提供者的名称
 *      * fallback : 配置熔断发生降级方法
 *                  实现类
 */
@FeignClient(name="imgs")
public interface ProductFeignClient {

	/**
	 * 配置需要调用的微服务接口
	 */
	@RequestMapping(value="/upload/{id}",method = RequestMethod.GET)
    public Result upload(MultipartFile img);
}
