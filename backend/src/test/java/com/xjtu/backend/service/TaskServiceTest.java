/*
 * @Descripttion: 
 * @Author: Ashley
 * @Date: 2020-04-12 18:12:00
 */
package com.xjtu.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.xjtu.backend.store.TaskStore;
import com.xjtu.backend.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskServiceTest {
    //mock和injectmock用法区别
    @Mock
    private TaskStore taskStore;

    @InjectMocks
    private TaskService taskService=new TaskService();

    private ArrayList<Task> tasks;

    @BeforeEach
    void setUp(){//先整体初始化
        tasks=new ArrayList<>();
    }

    @Test
    public void saveTask(){
        when(taskStore.readTask()).thenReturn(tasks);
        Task savedTask=taskService.saveTask(new Task(1L, "newTask"));
        assertNotNull(savedTask.getUpdateTime());
        verify(taskStore).writeTask(any());
    }

    @Test
    public void getAllTask(){
        when(taskStore.readTask()).thenReturn(tasks);
        List<Task> all=taskService.getAllTask();
        assertEquals(tasks,all);
    }

    @Test
    public void FindTask(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTask()).thenReturn(tasks);
        Optional<Task> optionTask=taskService.findTask(1L);
        Task task=optionTask.get();
        assertEquals(1L, task.getId());
        assertEquals("task", task.getContent());
    }

    @Test
    public void getEmptyTask(){
        when(taskStore.readTask()).thenReturn(tasks);
        Optional<Task> opTask=taskService.findTask(1L);
        assertFalse(opTask.isPresent());
    }

    @Test
    public void updateTask(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTask()).thenReturn(tasks);
        Optional<Task> opTask=taskService.updateTask(new Task(1L, "new Task"));
        Task task=opTask.get();
        assertEquals(1L, task.getId());
        assertEquals("new Task", task.getContent());
        assertNotNull(task.getUpdateTime());
        verify(taskStore).writeTask(any());
    }

    @Test
    public void shouldNotUpdateTaskWhenNotExit(){
        when(taskStore.readTask()).thenReturn(tasks);
        Optional<Task> opTask=taskService.updateTask(new Task(1L, "new Task"));
        assertFalse(opTask.isPresent());
        verify(taskStore,new Times(0)).writeTask(any());
    }

    @Test
    public void deleteTask(){
        tasks.add(new Task(1L,"task"));
        when(taskStore.readTask()).thenReturn(tasks);
        Optional<Task> opTask=taskService.deleteTask(1L);
        Task task=opTask.get();
        assertEquals(1L, task.getId());
        verify(taskStore).writeTask(any());
    }

    @Test
    public void shouldNotDeleteTaskWhenNotExist(){
        when(taskStore.readTask()).thenReturn(tasks);
        Optional<Task> opTask=taskService.deleteTask(1L);
        assertFalse(opTask.isPresent());
        verify(taskStore,new Times(0)).writeTask(any());
    }

}