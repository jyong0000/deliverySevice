package com.deliveryservice.entity;

import com.deliveryservice.constant.MenuStatus;
import com.deliveryservice.dto.MenuFormDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu")
@Getter
@Setter
@ToString
public class Menu {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String menuNm;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private String menuDetail;
	
	@Enumerated(EnumType.STRING)
	private MenuStatus menuStatus;
	
	public void updateMenu(MenuFormDto menuFormDto) {
		this.menuNm = menuFormDto.getMenuNm();
		this.price = menuFormDto.getPrice();
		this.menuDetail = menuFormDto.getMenuDetail();
		this.menuStatus = menuFormDto.getMenuStatus();
	}
	
	
}
