/*
 * @Descripttion: 
 * @Author: Ashley
 * @Date: 2020-04-12 18:12:28
 */
package com.xjtu.backend.store;

import java.util.Arrays;

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
        
    }

    @Test
    public void testWriteTask(){
        
    }

    private Task createTask(long l, String test) {
        Task task=new Task(l,test);
        task.setUpdateTime();
        return task;
    }


}