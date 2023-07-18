package com.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_img")
@Getter
@Setter
public class MenuImg {

	@Id
	@Column(name = "menu_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String menuImgName;
	
	private String menuOriImgName;
	
	private String menuImgUrl;
	
	private String repimgYn;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;
	
	public void updateMenuImg(String menuOriImgName, String menuImgName, String menuImgUrl) {
		this.menuOriImgName = menuOriImgName;
		this.menuImgName = menuImgName;
		this.menuImgUrl = menuImgUrl;
	}
}
