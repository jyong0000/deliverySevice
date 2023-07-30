package com.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_menu")
@ToString
@Getter
@Setter
public class OrderMenu extends BaseEntity{
	@Id
	@Column(name = "order_menu_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;
	
	private int orderPrice;
	
	private int count;
	
	public static OrderMenu createOrderMenu(Menu menu, int count) {
		OrderMenu orderMenu = new OrderMenu();
		orderMenu.setMenu(menu);
		orderMenu.setCount(count);
		orderMenu.setOrderPrice(menu.getPrice());
		
		return orderMenu;
	}
	
	public int getTotalPrice() {
		return orderPrice * count;
	}
	
}
