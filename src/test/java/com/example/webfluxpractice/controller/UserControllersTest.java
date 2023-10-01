package com.example.webfluxpractice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.webfluxpractice.controllers.UserControllers;
import com.example.webfluxpractice.entity.Users;
import com.example.webfluxpractice.service.UserServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(UserControllers.class)
public class UserControllersTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private UserServiceImpl userServiceImpl;

	@Test
	public void saveTest() {
		Mono<Users> userMono = Mono.just(new Users(1, "Yoga", 23, 35000.0, "EEE"));
		Users users = new Users(1, "Yoga", 23, 35000.0, "EEE");
		when(userServiceImpl.createUsers(users)).thenReturn(userMono);

		webTestClient.post().uri("/create").body(Mono.just(userMono), Users.class).exchange().expectStatus().isOk();
	}

	@Test
	public void getAllProducts() {
		Flux<Users> userFlux = Flux.just(new Users(1, "Yoga", 23, 35000.0, "EEE"),
				new Users(2, "sandy", 16, 5000.0, "Doctor"));

		when(userServiceImpl.getAll()).thenReturn(userFlux);

		Flux<Users> responseBody = webTestClient.get().uri("/list").exchange().expectStatus().isOk()
				.returnResult(Users.class).getResponseBody();

		StepVerifier.create(responseBody).expectSubscription().expectNext(new Users(1, "Yoga", 23, 35000.0, "EEE"))
				.expectNext(new Users(2, "sandy", 16, 5000.0, "Doctor")).verifyComplete();

	}

	@Test
	public void putTest() {
		Mono<Users> userMono = Mono.just(new Users(1, "Yoga", 23, 3500.0, "EEE"));
		Users users = new Users(1, "Yoga", 23, 35000.0, "EEE");
		when(userServiceImpl.updateById(String.valueOf(1), users)).thenReturn(userMono);

		webTestClient.put().uri("/update/1").body(Mono.just(userMono), Users.class).exchange().expectStatus().isOk();
	}
	
	@Test
	public void deleteMapping() {
		when(userServiceImpl.deleteByid(any())).thenReturn(Mono.empty());
		
		webTestClient.delete().uri("/4").exchange().expectStatus().isOk();
	}

}
