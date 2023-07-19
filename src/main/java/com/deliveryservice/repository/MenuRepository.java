package com.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliveryservice.entity.Menu;


public interface MenuRepository extends JpaRepository<Menu, Long>,MenuRepositoryCustom{

}
