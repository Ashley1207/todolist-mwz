/*
 * @Descripttion: 1.model层，创建接口属性
 * @Author: Ashley
 * @Date: 2020-04-12 09:19:04
 */
package com.xjtu.backend.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
//import org.apache.tomcat.jni.Local;
@Component
public class Task {
    private long id;
    private String content;
    private LocalDateTime updatedAt;

    public Task() {
    }

    public Task(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setContent(String content) {
        this.content = content;
    }
}
