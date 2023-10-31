package com.muhib.clickdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ClickdownApplication {

//	@Bean
//	public WebMvcConfigurer cors(){
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry
//						.addMapping("/**")
//						.allowedOrigins("*")
//						.allowedMethods("*");
//				WebMvcConfigurer.super.addCorsMappings(registry);
//			}
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(ClickdownApplication.class, args);
	}

}