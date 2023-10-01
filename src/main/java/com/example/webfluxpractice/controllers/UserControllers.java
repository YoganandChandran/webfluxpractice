package com.example.webfluxpractice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webfluxpractice.entity.Users;
import com.example.webfluxpractice.service.UserServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class UserControllers {
	
	private final UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserControllers(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}
	
	@PostMapping("/create")
	public Mono<Users> createUsers(@RequestBody Users users){
		return userServiceImpl.createUsers(users);
	}
	
	@GetMapping(value = "/list")
	public Flux<Users> listCustomers(){
		return userServiceImpl.getAll();
	}
	
	@PutMapping("/update/{id}")
	public Mono<Users> updateById(@PathVariable("id") String id,@RequestBody Users users){
		return userServiceImpl.updateById(id, users);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable("id") String id){
		return userServiceImpl.deleteByid(id);
	}
}
