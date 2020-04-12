/*
 * @Descripttion: 1.model层，创建接口属性
 * @Author: Ashley
 * @Date: 2020-04-12 09:19:04
 */
package com.xjtu.backend.model;

import java.time.LocalDateTime;
//import org.apache.tomcat.jni.Local;
public class Task {
    private long id;
    private String content;//todo内容
    private LocalDateTime updateAt;//TODO时间
    //构造器
    public Task(){
    }
    //重写构造器，实现特定功能
    public Task(long id,String content){
        this.id=id;
        this.content=content;
    }
    public long getId(){
        return id;
    }
    public String getContent(){
        return content;
    }
    public LocalDateTime getUpdateTime(){
        return updateAt;
    }
    public void setUpdateTime(){
        this.updateAt= LocalDateTime.now();
    }
    public void setContent(String content){
        this.content=content;
    }


}