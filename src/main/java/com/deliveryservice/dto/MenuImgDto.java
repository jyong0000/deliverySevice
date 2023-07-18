package com.deliveryservice.dto;

import org.modelmapper.ModelMapper;

import com.deliveryservice.entity.MenuImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuImgDto {
	private Long id;
	
	private String menuImgName;
	
	private String menuOriImgName;
	
	private String menuImgUrl;
	
	private String repimgYn;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static MenuImgDto of (MenuImg menuimg) {
		return modelMapper.map(menuimg, MenuImgDto.class);		
	}
}
