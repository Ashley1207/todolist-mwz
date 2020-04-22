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
//import org.thymeleaf.expression.Arrays;

import java.util.Optional;
//import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;


@Service
public class TaskService {
    @Autowired
    public TaskStore store;

    public List<Task> getAll() {
        return store.readTasks();
    }

    public Task saveTask(Task task) {
        List<Task> tasks = new ArrayList<>(store.readTasks());
        task.setUpdatedAt();
        tasks.add(task);
        store.writeTasks(tasks);
        return task;
    }

    public Optional<Task> find(Long id) {
        //stream流：stream of elenment------->filter->sorted->map->collect
        return store.readTasks().stream().filter(task -> task.getId() == id).findAny();
    }

    public Optional<Task> update(Task task) {
        List<Task> tasks = new ArrayList<>(store.readTasks());
        Optional<Task> any = tasks.stream().filter(task1 -> task1.getId() == task.getId()).findAny();
        if (any.isPresent()) {
            any.get().setContent(task.getContent());
            any.get().setUpdatedAt();
            store.writeTasks(tasks);
        }
        return any;
    }
   /**
     * @name: Optional类：
     * 
     * @param {type} 
     * @return: 
     */
    public Optional<Task> delete(Long id) {
        List<Task> tasks = store.readTasks();
        Optional<Task> any = tasks.stream().filter(task1 -> task1.getId() == id).findAny();
        if (any.isPresent()) {
            store.writeTasks(tasks.stream().filter(task -> task.getId() != id).collect(Collectors.toList()));
            return any;
        }
        return any;
    }
}
