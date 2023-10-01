package com.example.webfluxpractice.utils;

import org.springframework.beans.BeanUtils;

import com.example.webfluxpractice.entity.Users;

public class AppUtils {

	 public static Users entityToDto(Users users) {
	        Users user = new Users();
	        BeanUtils.copyProperties(users, user);
	        return user;
	    }
	 
	 public static Users dtoToEntity(Users users) {
	        Users user = new Users();
	        BeanUtils.copyProperties(user, users);
	        return user;
	    }

}
