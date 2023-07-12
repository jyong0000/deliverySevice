package com.deliveryservice.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.deliveryservice.dto.UserFormDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	@GetMapping(value = "/new")
	public String userForm(Model model) {
		return "user/userForm";
	}
	
	@PostMapping(value = "/new")
	public String userForm(@Valid UserFormDto userFormDto, 
			BindingResult bindingResult, Model model) {
	
		if(bindingResult.hasErrors()) {
			return "user/userForm";
		}
		return "redirect:/";
	}
	
	//로그인 화면 
	@GetMapping(value ="/users/login")
	public String loginUser() {
		return "user/userLogin";
	}
	
	
}
