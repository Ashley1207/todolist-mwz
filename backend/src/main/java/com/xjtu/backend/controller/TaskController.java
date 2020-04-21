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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
//import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




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

    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        //TODO: process POST request
        taskService.saveTask(task);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/tasks/{id}")
                      .buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    /**
     * postmapping:倾向于添加信息
     * putmapping:倾向于更新信息
     */
    @PutMapping(path="/{id}",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        //TODO: process PUT request
        Optional<Task> updateTask=taskService.updateTask(new Task(id,task.getContent()));
        
        return ResponseEntity.of(updateTask);
    }
    
    @DeleteMapping(path="/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        Optional<Task> deleteTask=taskService.deleteTask(id);
        if(deleteTask.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}