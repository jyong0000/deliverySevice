package com.deliveryservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.deliveryservice.constant.OrderStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@ToString
@Getter
@Setter
public class Order {
	
	@Id
	@Column(name="order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, 
			orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderMenu> orderMenus = new ArrayList<>();
	
	public void addOrderMenu(OrderMenu orderMenu) {
		this.orderMenus.add(orderMenu);
		orderMenu.setOrder(this);
	}
	
	public static Order createOrder(User user, List<OrderMenu> orderMenuList) {
		Order order = new Order();
		order.setUser(user);
		
		for(OrderMenu orderMenu : orderMenuList) {
			order.addOrderMenu(orderMenu);
		}
		
		order.setOrderStatus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.now());
		
		return order;
	}
	
	public int getTotalPrice() {
		int totalPrice = 0;
		for(OrderMenu orderMenu : orderMenus) {
			totalPrice += orderMenu.getTotalPrice();
		}
		return totalPrice;
	}
	
	public void cancelOrder() {
		this.orderStatus = OrderStatus.CANCEL;
		
	}
}
