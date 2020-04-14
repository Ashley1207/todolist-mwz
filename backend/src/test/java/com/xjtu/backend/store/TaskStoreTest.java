/*
 * @Descripttion: 
 * @Author: Ashley
 * @Date: 2020-04-12 18:12:28
 */
package com.xjtu.backend.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.xjtu.backend.model.Task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskStoreTest {

    @Autowired
    private TaskStore taskStore;
    //在每个测试之后执行
    @AfterEach
    void tearDown() {
        taskStore.writeTask(Arrays.asList(createTask(1L, "test")));
    }

    @Test
    public void testReadTask() {
        List<Task> tasks=taskStore.readTask();
        assertEquals(1, tasks.size());
        assertEquals(1L, tasks.get(0).getId());
        assertEquals("test", tasks.get(0).getContent());
        assertEquals(LocalDateTime.of(2020, 4, 12, 0, 0), tasks.get(0).getUpdateTime());
        
    }

    @Test
    public void testWriteTask(){
        List<Task> tasks=Arrays.asList(createTask(1L, "task 1"),createTask(2L, "task 2"));
        taskStore.writeTask(tasks);
        List<Task> tasksInStore=taskStore.readTask();
        assertEquals(2, tasksInStore.size());
        assertNotNull(tasksInStore.get(1).getUpdateTime());
        assertEquals("task 2", tasksInStore.get(1).getContent());

    }

    private Task createTask(long l, String test) {
        Task task=new Task(l,test);
        task.setUpdateTime();
        return task;
    }


}