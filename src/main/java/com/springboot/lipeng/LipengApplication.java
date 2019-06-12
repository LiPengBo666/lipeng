package com.springboot.lipeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LipengApplication{

	public static void main(String[] args) {
		SpringApplication.run(LipengApplication.class, args);
	}
//继承extends SpringBootServletInitializer,重写configure方法,使用位置tomcat启动项目
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(LipengApplication.class);
//	}
}
