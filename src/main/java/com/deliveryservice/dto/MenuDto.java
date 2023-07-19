package com.deliveryservice.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {
	private Long id;

	private String menuNm;
	
	private String menuDetail;
	
	private String menuImgUrl;
	
	private Integer price;
	
	@QueryProjection
	public MenuDto(Long id, String menuNm, String menuDetail, String menuImgUrl, Integer price) {
		this.id = id;
		this.menuNm = menuNm;
		this.menuDetail = menuDetail;
		this.menuImgUrl = menuImgUrl;
		this.price = price;
	}
}
