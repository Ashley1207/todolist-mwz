/*
 * @Descripttion: controller层，负责页面的访问控制
 * @Author: Ashley
 * @Date: 2020-04-12 09:13:36
 */
package com.xjtu.backend.controller;

import com.xjtu.backend.service.TaskService;
import com.xjtu.backend.model.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//import org.springframework.web.bind.annotation.RequestParam;


@RestController // 标记这个类是controller
@RequestMapping("/api/tasks")//映射http请求，指定访问的Url路径，对应浏览器访问的地址，访问该路径则执行以下函数
public class TaskController {
    @Autowired
    public TaskService taskService;
    //get请求，返回json数据
    @GetMapping(produces = "/application/json")
    public List<Task> listTask(){
        return taskService.getAllTask();
    }
    /**
         * @name: ResponseEntity:处理http响应
         * @PathVariable：接受请求路径中id的值
         * @param {type} 
         * @return: 返回id
         */
    @GetMapping("/{id}")
    public ResponseEntity<Task> findTask(@PathVariable long id) {
        
        return ResponseEntity.of(taskService.findTask(id));
    }
    


}