package com.deliveryservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.deliveryservice.constant.MenuStatus;
import com.deliveryservice.dto.MenuFormDto;
import com.deliveryservice.execption.OutOfStockException;

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
	private int stockNumber;
	
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
		this.stockNumber = menuFormDto.getStockNumber();
		this.menuDetail = menuFormDto.getMenuDetail();
		this.menuStatus = menuFormDto.getMenuStatus();
	}
	
	//재고를 감소시킨다.
	public void removeStock(int stockNumber) {
		int restStock = this.stockNumber - stockNumber; //남은 재고 수량
		
		if(restStock < 0) {
			throw new OutOfStockException("상품의 재고가 부족합니다. "
					+ "현재 재고수량: " + this.stockNumber);
		}
		
		this.stockNumber = restStock; //남은 재고수량 반영
	}
	
	//재고 증가
	public void addStock(int stockNumber) {
		this.stockNumber += stockNumber;
	}
	 
	}
	
