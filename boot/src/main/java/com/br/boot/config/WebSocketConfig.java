package com.br.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.br.boot.handler.ChatEchoHandler;

import lombok.RequiredArgsConstructor;

@EnableWebSocket
@RequiredArgsConstructor
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
	
	private final ChatEchoHandler chatEchoHandler;
	
	/*
	<websocket:handlers>
		<websocket:mapping handler="chatEchoHandler" path="/chat" />
		<websocket:handshake-interceptors>
			<beans:bean class="org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor" />
		</websocket:handshake-interceptors>
		<websocket:sockjs />
	</websocket:handlers>
	*/
	
	@Bean
	HttpSessionHandshakeInterceptor httpSessionHandshakeInterceptor() {
		return new HttpSessionHandshakeInterceptor();
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatEchoHandler, "/chat")
				.addInterceptors(httpSessionHandshakeInterceptor())
				.withSockJS();
	}

}
