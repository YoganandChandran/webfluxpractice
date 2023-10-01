package com.example.webfluxpractice.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.webfluxpractice.entity.Users;
import com.example.webfluxpractice.repository.UserRepository;
import com.example.webfluxpractice.utils.AppUtils;

import jakarta.persistence.EntityNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl {

	private final UserRepository userRepository;

	@Autowired
	private UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Mono<Users> createUsers(Users users) {
		return userRepository.save(users);
	}

	public Flux<Users> getAll() {
		return userRepository.findAll().map(AppUtils::entityToDto).log();
	}

	public Mono<ServerResponse> getStreamAll(ServerRequest request) {
		Flux<Users> users = userRepository.findAll().delayElements(Duration.ofSeconds(1));
		return ServerResponse.ok().body(users,Users.class);
	}
	
	public Mono<ServerResponse> getUsersbyId (ServerRequest request){
		Integer userId =Integer.valueOf(request.pathVariable("input"));
		Mono<Users> user = userRepository.findAll().filter(u->u.getId()==userId).next();
		return ServerResponse.ok().body(user,Users.class);
	}
	
	public Mono<Users> findById(String id){
		return userRepository.findAll().filter(u->u.getId()==Integer.valueOf(id)).next();
	}
	
	public Mono<Users> updateById(String id,Users user){
		Mono<Users> existsUser =  userRepository.findById(Integer.valueOf(id));
					return existsUser.doOnNext(u->{
						u.setAge(user.getAge());
						u.setDepartment(user.getDepartment());
						u.setName(user.getName());
						u.setSalary(user.getSalary());
					}).flatMap(userRepository::save)
					  .switchIfEmpty(Mono.error(new EntityNotFoundException("This id Not Found "+id)));
	}
	
	public Mono<Void> deleteByid(String id){
		return userRepository.deleteById(Integer.valueOf(id));
	}

}
