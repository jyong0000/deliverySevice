package com.deliveryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliveryservice.constant.MenuStatus;
import com.deliveryservice.entity.Menu;


public interface MenuRepository extends JpaRepository<Menu, Long> , MenuRepositoryCustom{
	
	List<Menu> findByMenuNm(String menuNm);
	
	List<Menu> findByMenuNmAndMenuStatus(String menuNm, MenuStatus menuStatus);
	

}
