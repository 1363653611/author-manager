package com.zbcn.authormanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zbcn8
 */
@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@MapperScan("com.zbcn.authormanager.*.mapper")
public class AuthorManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorManagerApplication.class, args);
	}

}
