/*
 * @Descripttion: controller层，负责页面的访问控制
 * @Author: Ashley
 * @Date: 2020-04-12 09:13:36
 */
package com.xjtu.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 标记这个类是controller
@RequestMapping("/api/tasks")//映射http请求，指定访问的Url路径，对应浏览器访问的地址，访问该路径则执行以下函数
public class TaskController {

}