package com.zxe.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Elaine
 * @Description:
 * @Date: Created in 1:48 PM 2020/10/28
 * @Version: 1.0
 */
@RestController
public class Controller {

    @GetMapping("/hello")
    public String hello(){
        return "hello springboot11";
    }

}
