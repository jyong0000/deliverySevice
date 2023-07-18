package com.deliveryservice.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.deliveryservice.constant.StoreStatus;
import com.deliveryservice.entity.Store;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreFormDto {
	private Long id;

	@NotBlank(message = "가게명은 필수 입력입니다.")
	private String storeNm;
	
	private StoreStatus storeStatus;
	
	private List<StoreImgDto> storeImgDtoList = new ArrayList<>();
	
	private List<Long> storeImgIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Store createStore() {
		return modelMapper.map(this, Store.class);
	}
	
	public static StoreFormDto of (Store store) {
		return modelMapper.map(store, StoreFormDto.class);
	}
}
