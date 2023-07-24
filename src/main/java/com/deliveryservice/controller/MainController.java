package com.deliveryservice.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.deliveryservice.dto.MenuDto;
import com.deliveryservice.dto.MenuSearchDto;
import com.deliveryservice.service.MenuService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final MenuService menuService;
	
	@GetMapping(value= "/")
	public String main(MenuSearchDto menuSearchDto, Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<MenuDto> menus = menuService.getMenuPage(menuSearchDto, pageable);
		
		model.addAttribute("menus" ,menus);
		model.addAttribute("menuSearchDto", menuSearchDto);
		
		return "main";
	}
}
