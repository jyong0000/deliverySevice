package com.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_history")
@ToString
@Getter
@Setter
public class OrderHistory {
	@Id
	@Column(name = "order_history_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "order_menu_id")
	private OrderMenu orderMenu;
}
