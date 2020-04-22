/*
 * @Descripttion: 
 * @Author: Ashley
 * @Date: 2020-04-21 17:28:05
 */
package com.xjtu.backend.store;

import com.xjtu.backend.model.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskStoreTest {
    @Autowired
    private TaskStore taskStore;
    //在每个测试之后执行
    @AfterEach
    void tearDown() {
        taskStore.writeTasks(Arrays.asList(createTask(1L, "test")));
    }

    @Test
    public void shouldReadTasks() {
        List<Task> tasks = taskStore.readTasks();
        assertEquals(1, tasks.size());//判断readTask()方法得到的List集合个数是否为1
        assertEquals(1L, tasks.get(0).getId());//判断tasks对象中第一个的id是否为1
        assertEquals("test", tasks.get(0).getContent());//判断tasks对象中第一个的内容是否为test
        assertEquals(LocalDateTime.of(2020, 4, 12, 0, 0), tasks.get(0).getUpdatedAt());
    }

    @Test
    public void shouldWriteTasks() {
        List<Task> tasks = Arrays.asList(createTask(1L, "task 1"), createTask(2L, "task 2"));//列表存入两个新建Task对象
        taskStore.writeTasks(tasks);//写入文件
        List<Task> tasksInStore = taskStore.readTasks();//读取文件中的Task
        assertEquals(2, tasksInStore.size());
        assertNotNull(tasksInStore.get(1).getUpdatedAt());
        assertEquals("task 2", tasksInStore.get(1).getContent());
    }

    private Task createTask(long l, String test) {//创建一个Task对象
        Task task = new Task(l, test);
        task.setUpdatedAt();
        return task;
    }
}
