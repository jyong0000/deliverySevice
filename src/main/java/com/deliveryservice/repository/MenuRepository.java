package com.deliveryservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deliveryservice.constant.MenuStatus;
import com.deliveryservice.entity.Menu;


public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom{
	
	List<Menu> findByMenuNm(String menuNm);
	
	List<Menu> findByMenuNmAndMenuStatus(String menuNm, MenuStatus menuStatus);
	
	List<Menu> findByPriceBetween(int price1, int price2);
	
	List<Menu> findByMenuStatusNotNull();
	
	List<Menu> findByMenuDetailLike(String menuDetail);
	
	List<Menu> findByPriceLessThanOrderByPriceDesc(int price);
	
	@Query("select i from Menu i where i.menuDetail "
			+ "like %:menuDetail% order by i.price desc")
	List<Menu> findByMenuDetail(@Param("menuDetail") String menuDetail);
	
	@Query(value = "select * from menu where menu_detail" 
				+ "like %:menuDetail% order by price desc" , nativeQuery = true)
	List<Menu> findByItemDetailByNative(@Param("menuDetail") String menuDetail);
	

}
