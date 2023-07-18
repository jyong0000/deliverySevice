package com.deliveryservice.dto;

import org.modelmapper.ModelMapper;

import com.deliveryservice.entity.StoreImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreImgDto {
	private Long id;
	
	private String storeImgName;
	
	private String storeOriImgName;
	
	private String storeImgUrl;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static StoreImgDto of(StoreImg storeImg) {
		return modelMapper.map(storeImg, StoreImgDto.class);
	}
}
