package com.deliveryservice.entity;

import com.deliveryservice.constant.StoreStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="store")
@ToString
@Getter
@Setter
public class Store {

	@Id
	@Column(name="store_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private StoreStatus storeStatus; 
}
