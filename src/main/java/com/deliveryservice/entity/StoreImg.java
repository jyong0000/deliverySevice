package com.deliveryservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store_img")
@Getter
@Setter
public class StoreImg {
	
	@Id
	@Column(name = "store_img_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String storeImgName;
	
	private String storeOriImgName;
	
	private String storeImgUrl;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private Store store;
	
	public void updateStoreImg(String storeOriImgName, String storeImgName, String storeImgUrl) {
		this.storeOriImgName = storeOriImgName;
		this.storeImgName = storeImgName;
		this.storeImgUrl = storeImgUrl;
	}

}
