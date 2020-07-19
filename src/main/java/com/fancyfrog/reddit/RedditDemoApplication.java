package com.fancyfrog.reddit;

import com.fancyfrog.reddit.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@Import(SwaggerConfig.class)
public class RedditDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditDemoApplication.class, args);
	}

}
