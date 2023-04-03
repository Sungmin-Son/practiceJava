package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
				.version("v1")
				.title("swagger API 서비스")
				.description("swagger API 예시글 입니다.");
		
		return new OpenAPI()
				.info(info);
	}

}
