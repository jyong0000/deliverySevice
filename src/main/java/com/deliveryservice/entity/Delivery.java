package com.deliveryservice.entity;

import com.deliveryservice.constant.DeliveryStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery")
@ToString
@Getter
@Setter
public class Delivery {
	@Id
	@Column(name = "delivery_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatus deliveryStatus;
	
	@ManyToOne
	@JoinColumn(name = "order_menu_id")
	private OrderMenu orderMenu;
	
}
