/*
 * @Descripttion: 
 * @Author: Ashley
 * @Date: 2020-04-12 12:35:01
 */
package com.xjtu.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.xjtu.backend.store"})//要加一个注解，不然扫描不出来，坑死了
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
