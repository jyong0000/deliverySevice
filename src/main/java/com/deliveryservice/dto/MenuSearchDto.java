package com.deliveryservice.dto;


import com.deliveryservice.constant.MenuStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuSearchDto {
	private String searchDateType;
	private MenuStatus searchmenuStatus;
	private String searchBy;
	private String searchQuery = "";
}
