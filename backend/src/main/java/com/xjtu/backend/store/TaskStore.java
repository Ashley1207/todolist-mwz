/*
 * @Descripttion: 对task操作
 * @Author: Ashley
 * @Date: 2020-04-12 09:15:31
 */
package com.xjtu.backend.store;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.*;
import com.xjtu.backend.model.Task;

//import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Value;
@
public class TaskStore {
    @Value("${todo.store.filename}")
    private String filename;
    @Value("${env}")
    private String env;

    public List<Task> readTask(){
        try {
            //将整个文件读入内存并返回一个字节数组给string构造器，可能出现内存不足
            String content=new String(Files.readAllBytes(getFile().toPath()));
            Task[] tasks=getGson().fromJson(content, Task[].class);//将json数据转化为java对象
            return Arrays.asList(tasks);//将数组转化为List集合
            
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return Collections.emptyList();//返回一个不可变的列表
        
    }

    public void writeTask(List<Task> list){
        try {
            //创建一个filewrite对象，并将数据放在指定目录
            FileWriter fileWriter=new FileWriter(getFile().getAbsolutePath());
            //将task转化为json串并写入流中
            fileWriter.write(getGson().toJson(list));
            fileWriter.flush();//将数据刷新到目的地
            fileWriter.close();//关闭流资源
            
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    private File getFile(){
        //获取文件路径
        if(env.equals("test")){
            return new File(getClass().getClassLoader().getResource(filename).getFile());
        }
        
        return new File(filename);

    }


    private Gson getGson() {
        /**
         * @name: 自定义实现LocalDateTime类型的序列化和反序列化
         * @msg: 
         * 自定义反序列化的过程，返回相应的对象
         * JsonDeserializer<Id>() {
             public Id deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
         * @param {type} 
         * class IdSerializer implements JsonSerializer<Id>() {
                public JsonElement serialize(Id id, Type typeOfId, JsonSerializationContext context) {
                              return new JsonPrimitive(id.getValue());
         * toString()返回的是JsonElement的字符串，所有是带双引号的
           getAsString()返回的是JsonElement的字符串值，所以不带双引号
         */
        return new GsonBuilder()
                   .registerTypeAdapter(LocalDateTime.class,(JsonDeserializer<LocalDateTime>)
                   (json,typeOfT,context) -> LocalDateTime.parse(json.getAsString(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                   .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                   (localDateTime,typeOfT,context) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
                   .create();
    }

}

