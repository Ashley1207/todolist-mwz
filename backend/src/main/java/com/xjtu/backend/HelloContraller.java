/*
 * @Descripttion: 
 * @Author: Ashley
 * @Date: 2020-04-12 09:08:08
 */
package com.xjtu.backend;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class HelloContraller {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

}