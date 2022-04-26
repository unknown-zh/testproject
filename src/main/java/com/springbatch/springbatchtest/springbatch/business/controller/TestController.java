package com.springbatch.springbatchtest.springbatch.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController.java
 * @PackagePath com.springbatch.springbatchtest.springbatch.business.controller
 * @Author Dr. zhang
 * @CreateTime 2022/4/26
 * @Description
 */
@RestController
@RequestMapping("test-controller")
public class TestController {

    @GetMapping("/test")
    public int testController (){
        System.out.println("111111111111111");
        return 1;
    }
}
