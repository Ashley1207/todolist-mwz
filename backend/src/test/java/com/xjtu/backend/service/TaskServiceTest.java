package com.xjtu.backend.service;

import com.xjtu.backend.model.Task;
import com.xjtu.backend.store.TaskStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest {
    @Mock
    private TaskStore taskStore;

    @InjectMocks
    private TaskService taskService = new TaskService();

    private ArrayList<Task> tasks;

    @BeforeEach
    void setUp(){   //实现测试前的初始化工作
        tasks=new ArrayList<>();
    }

    @Test
    public void shouldSaveTask(){
        //是指定当执行了这个方法的时候，返回 thenReturn 的值，相当于是对模拟对象的配置过程，为某些条件给定一个预期的返回值。
        when(taskStore.readTask()).thenReturn(tasks);

        Task savedTask=taskService.saveTask(new Task(1L, "newTask"));
        assertNotNull(savedTask.getUpdateTime());   //断言更新时间不为空
        verify(taskStore).writeTask(any()); //验证taskStore的writeTask是否被调用
    }

    @Test
    public void shouldGetAllTask(){
        when(taskStore.readTask()).thenReturn(tasks);

        List<Task> all=taskService.getAllTask();

        assertEquals(tasks,all);
    }

    @Test
    public void ShouldFindTask(){
        tasks.add(new Task(1L, "task"));
        when(taskStore.readTask()).thenReturn(tasks);

        Optional<Task> optionTask=taskService.findTask(1L);

        Task task=optionTask.get();
        assertEquals(1L, task.getId());
        assertEquals("task", task.getContent());
    }

    @Test
    public void shouldGetEmptyTask(){
        when(taskStore.readTask()).thenReturn(tasks);
        Optional<Task> opTask=taskService.findTask(1L);
        assertFalse(opTask.isPresent());
    }

    @Test
    public void shouldUpdateTask(){
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
    public void shouldDeleteTask(){
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