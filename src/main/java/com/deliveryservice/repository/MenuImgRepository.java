package com.deliveryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliveryservice.entity.MenuImg;


public interface MenuImgRepository extends JpaRepository<MenuImg, Long>{
	List<MenuImg> findByMenuIdOrderByIdAsc(Long menuId);
	
	MenuImg findByMenuIdAndRepimgYn(Long menuId, String repimgYn);
}
