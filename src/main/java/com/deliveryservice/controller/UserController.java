package com.deliveryservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	//로그인 화면 
	@GetMapping(value ="/users/login")
	public String loginUser() {
		return "user/userLogin";
	}
}
