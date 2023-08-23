package com.deliveryservice.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.deliveryservice.dto.MenuDto;
import com.deliveryservice.dto.MenuFormDto;
import com.deliveryservice.dto.MenuSearchDto;
import com.deliveryservice.entity.Menu;
import com.deliveryservice.service.MenuService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {
	private final MenuService menuService;
	
	//메뉴 리스트
	@GetMapping(value = "/menu/shop")
	public String menuShopList(Model model, MenuSearchDto menuSearchDto,
								Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
		Page<MenuDto> menus = menuService.getMenuPage(menuSearchDto, pageable);
		
		model.addAttribute("menus", menus);
		model.addAttribute("menuSearchDto", menuSearchDto);
		model.addAttribute("maxPage",5);
		
		return "menu/menuShopList"; 
	}
	
	//메뉴 상세페이지
	@GetMapping(value ="/menu/{menuId}")
	public String menuDtl(Model model, @PathVariable("menuId") Long menuId) {
		MenuFormDto menuFormDto = menuService.getMenuDtl(menuId);
		model.addAttribute("menu", menuFormDto);
		return "menu/menuDtl";
	}
	
	//메뉴 등록페이지
	@GetMapping(value = "/admin/menu/new")
	public String menuForm(Model model) {
		model.addAttribute("menuFormDto",new MenuFormDto());
		return "menu/menuForm";
	}
	
	
	//메뉴, 메뉴이미지 등록
	@PostMapping(value = "/admin/menu/new")
	public String menuNew(@Valid MenuFormDto menuFormDto, BindingResult bindingResult,
			Model model, @RequestParam("menuImgFile") List<MultipartFile> menuImgfileList) {
		
		if(bindingResult.hasErrors()) {
			return "menu/menuForm";
		}
		
		if(menuImgfileList.get(0).isEmpty()) {
			model.addAttribute("errorMessage", "메뉴 이미지는 필수입니다.");
			return "menu/menuForm";
		}
		
		try {
			menuService.saveMenu(menuFormDto, menuImgfileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "메뉴 등록중 에러발생");
			return "menu/menuForm";
		}
		
		
		return "redirect:/";
	}
	
	//메뉴관리 페이지
	@GetMapping(value = {"/admin/menus" , "/admin/menus/{page}"})
	public String menuManage(MenuSearchDto menuSearchDto,
			@PathVariable("page") Optional<Integer> page, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 ,3);
		
		Page<Menu> menus = menuService.getAdminMenuPage(menuSearchDto, pageable);
		
		model.addAttribute("menus", menus);
		model.addAttribute("menuSearchDto", menuSearchDto);
		model.addAttribute("maxPage", 5);
		
		return "menu/menuMng";
		
	}
	
	//메뉴 수정페이지
	@GetMapping(value = "/admin/menu/{menuId}")
	public String menuDtl(@PathVariable("menuId") Long menuId, Model model) {
		
		try {
			MenuFormDto menuFormDto = menuService.getMenuDtl(menuId);
			model.addAttribute("menuFormDto", menuFormDto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "메뉴 정보를 가져오는중 에러가 발생했습니다.");
			model.addAttribute("menuFormDto", new MenuFormDto());
			return "menu/menuForm";
		}
		return "menu/menuModifyForm";
	}
	
	//메뉴 수정
	@PostMapping(value = "/admin/menu/{menuId}")
	public String menuUpdate(@Valid MenuFormDto menuFormDto, Model model, 
			BindingResult bindingResult,
			@RequestParam("menuImgFile") List<MultipartFile> menuImgFileList) {
		
		if(bindingResult.hasErrors()) {
			return "menu/menuForm";
		}
		
		if(menuImgFileList.get(0).isEmpty() && menuFormDto.getId() == null) {
			model.addAttribute("errorMessage", "메뉴 이미지는 필수입니다.");
			return "menu/menuForm";
		}
		
		try {
			menuService.updateMenu(menuFormDto, menuImgFileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "메뉴 수정 중 에러가 발생했습니다.");
			return "menu/menuForm";
		}
		return "redirect:/";
	}
	
	@DeleteMapping(value = "/menu/{menuId}/delete")
	public @ResponseBody ResponseEntity deleteMenu(@PathVariable("menuId") Long menuId , 
			Principal principal) {
		menuService.deleteMenu(menuId);
		return new ResponseEntity<Long> (menuId, HttpStatus.OK);
	}

}
