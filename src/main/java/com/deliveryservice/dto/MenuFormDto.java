package com.deliveryservice.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.deliveryservice.constant.MenuStatus;
import com.deliveryservice.entity.Menu;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuFormDto {
	private Long id;
	
	@NotBlank(message = "메뉴명은 필수 입력입니다.")
	private String menuNm;
	
	@NotNull(message = "가격은 필수 입력입니다.")
	private int price;
	
	@NotBlank(message = "상품 상세설명은 필수 입력입니다.")
	private String menuDetail;
	
	private MenuStatus menuStatus;
	
	private List<MenuImgDto> menuImgDtoList = new ArrayList<>();
	
	private List<Long> menuImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Menu createMenu() {
		return modelMapper.map(this, Menu.class);
	}
	
	public static MenuFormDto of(Menu menu) {
		return modelMapper.map(menu, MenuFormDto.class);
	}
	
}
