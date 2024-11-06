package com.br.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {
	
	/*
	 * spring-security.xml 에서 BCryptPasswordEncoder 빈등록 구문 대체
	 * <bean class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" id="bcryptPwdEncoder"/>
	 */
	@Bean
	BCryptPasswordEncoder bcryptPwdEncoder() {
		return new BCryptPasswordEncoder();
	}

}
