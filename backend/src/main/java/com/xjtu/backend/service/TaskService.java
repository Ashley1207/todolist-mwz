/*
 * @Descripttion: service层，编写业务逻辑代码
 * @Author: Ashley
 * @Date: 2020-04-12 09:14:35
 */
package com.xjtu.backend.service;



import com.xjtu.backend.store.TaskStore;
import com.xjtu.backend.model.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Arrays;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;


@Service
public class TaskService {

    @Autowired
    public TaskStore taskstore;

    public List<Task> getAllTask() {
        return taskstore.readTask();
    }

    public Task saveTask(Task task){
        List<Task> tasks=new ArrayList<>(taskstore.readTask());
        task.setUpdateTime();
        tasks.add(task);
        taskstore.writeTask(tasks);
        return task;

    }

    public Optional<Task> findTask(long id){
        //stream流：stream of elenment------->filter->sorted->map->collect
        return taskstore.readTask().stream().filter(task -> task.getId()==id).findAny();
    }

}