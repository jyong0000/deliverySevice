package com.deliveryservice.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.deliveryservice.constant.Role;
import com.deliveryservice.dto.UserFormDto;

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
	
	
	public static User createUser(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {
		String password = passwordEncoder.encode(userFormDto.getPassword());
		
		User user = new User();
		user.setName(userFormDto.getName());
		user.setEmail(userFormDto.getEmail());
		user.setAddress(userFormDto.getAddress());
		user.setPassword(password);
		user.setPhone(userFormDto.getPhone());
		user.setRole(Role.ADMIN);
		
		return user;
	}
}
