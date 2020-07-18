package com.fancyfrog.reddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RedditDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditDemoApplication.class, args);
	}

}
