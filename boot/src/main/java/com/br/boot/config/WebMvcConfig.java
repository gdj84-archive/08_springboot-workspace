package com.br.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.br.boot.interceptor.LoginCheckInterceptor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final LoginCheckInterceptor loginCheckInterceptor;
	
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
		
		registry.addResourceHandler("/upload/**")
				.addResourceLocations("file:///upload/");
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		/*
		<interceptor>
			<mapping path="/member/myinfo.do" />
			<mapping path="/board/regist.do" />
			<beans:bean class="com.br.spring.interceptor.LoginCheckInterceptor" id="loginCheckInterceptor"/>
		</interceptor>
		*/
		
		registry.addInterceptor(loginCheckInterceptor)
				.addPathPatterns("/member/myinfo.do")
				.addPathPatterns("/board/regist.do");
		
	}

}
