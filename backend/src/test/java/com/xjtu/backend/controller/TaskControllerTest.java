/*
 * @Descripttion: 
 * @Author: Ashley
 * @Date: 2020-04-12 18:11:34
 */
package com.xjtu.backend.controller;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.xjtu.backend.controller.TaskController;
import com.xjtu.backend.model.Task;
import com.xjtu.backend.service.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private List<Task> tasks=new ArrayList<>();

    @BeforeEach
    void setUp(){
        tasks.add(new Task(1L, "task"));
    }

    @Test
    public void shouldGetAll() throws Exception{
        when(taskService.getAllTask()).thenReturn(tasks);
        this.mockMvc.perform(get("/api/tasks")).andDo(print())
        .andExpect(status().isOk()).andExpect(jsonPath("$[0].content")
        .value("task"));

    }

}