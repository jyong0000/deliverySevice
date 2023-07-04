package com.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_item")
@ToString
@Getter
@Setter
public class OrderItem {
	@Id
	@Column(name = "order_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int orderPrice;
	
	private int count;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu menu;
	
	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;
}
