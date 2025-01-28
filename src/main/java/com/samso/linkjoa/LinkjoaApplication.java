package com.samso.linkjoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LinkjoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkjoaApplication.class, args);
	}

}
