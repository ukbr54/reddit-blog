package com.fancyfrog.reddit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Ujjwal Gupta on Jul,2020
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket redditBlogApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("Reddit Blog API")
                .version("1.0")
                .description("API for Reddit Blog Application")
                .contact(new Contact("Ujjwal Gupta","https://github.com/ukbr54","ujjwal.kbr@gmail.com"))
                .license("Apache Licence Version 2.0")
                .build();
    }
}

