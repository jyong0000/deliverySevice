package com.deliveryservice.entity;

import com.deliveryservice.constant.Role;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="user")
@ToString
@Getter
@Setter
public class User {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String phone;
	
}
