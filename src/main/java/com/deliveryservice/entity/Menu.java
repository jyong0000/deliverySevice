package com.deliveryservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.deliveryservice.constant.MenuStatus;
import com.deliveryservice.dto.MenuFormDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu")
@Getter
@Setter
@ToString
public class Menu extends BaseEntity{

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
	
	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, 
			orphanRemoval = true, fetch = FetchType.LAZY) //연관관계의 주인 설정(외래키 지정)
	private List<MenuImg> menuImgs = new ArrayList<>();
	
	public void updateMenu(MenuFormDto menuFormDto) {
		this.menuNm = menuFormDto.getMenuNm();
		this.price = menuFormDto.getPrice();
		this.menuDetail = menuFormDto.getMenuDetail();
		this.menuStatus = menuFormDto.getMenuStatus();
	}
	
	
}
