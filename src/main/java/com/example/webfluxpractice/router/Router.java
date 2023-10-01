package com.example.webfluxpractice.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.webfluxpractice.service.UserServiceImpl;

@Configuration
public class Router {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction(){
		
		return RouterFunctions.route()
				.GET("/stream/listAll",userServiceImpl::getStreamAll)
				.GET("/stream/get/{input}",userServiceImpl::getUsersbyId)
				.build();
	}

}
