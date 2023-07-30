package com.deliveryservice.dto;

import com.deliveryservice.entity.OrderMenu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderMenuDto {
	
	public OrderMenuDto(OrderMenu orderMenu, String imgUrl) {
		this.menuNm = orderMenu.getMenu().getMenuNm();
		this.count = orderMenu.getCount();
		this.orderPrice = orderMenu.getOrderPrice();
		this.imgUrl = imgUrl;
	}
	
	private String menuNm;
	private int count;
	private int orderPrice;
	private String imgUrl;
}
