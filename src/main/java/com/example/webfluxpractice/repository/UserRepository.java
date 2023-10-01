package com.example.webfluxpractice.repository;


import org.springframework.data.domain.Range;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.example.webfluxpractice.entity.Users;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<Users, Integer>{

	//Mono<Users> findByAmountBetween(Range<Double> closed);

}
