package com.br.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	/*
	 * * WebMvcConfigurer 
	 * - 인터셉터 등록
	 * - 리소스 핸들링
	 * - 뷰리졸버 세팅
	 * - 메세지 변환 
	 *   등등
	 */
	
	// 리소스 핸들링 등록 (servlet-context.xml의 <resources> 태그 설정 대신)
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// <resources mapping="/resources/**" location="classpath:/static/" />
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("classpath:/static/");		
		
	}

}
