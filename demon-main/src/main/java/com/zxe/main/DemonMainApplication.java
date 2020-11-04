package com.zxe.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.zxe"})
@MapperScan(basePackages={"com.zxe.admin"})
public class DemonMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemonMainApplication.class, args);
	}
}
