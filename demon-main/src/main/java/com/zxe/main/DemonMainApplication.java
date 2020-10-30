package com.zxe.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.zxe.admin","com.zxe.main"})
public class DemonMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemonMainApplication.class, args);
	}

}
