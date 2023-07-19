package com.deliveryservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.deliveryservice.dto.MenuDto;
import com.deliveryservice.dto.MenuSearchDto;
import com.deliveryservice.entity.Menu;

public interface MenuRepositoryCustom {
	Page<Menu> getAdminMenuPage(MenuSearchDto menuSearchDto, Pageable pageable);
	
	Page<MenuDto> getMainMenuPage(MenuSearchDto menuSearchDto,  Pageable pageable);
	}
