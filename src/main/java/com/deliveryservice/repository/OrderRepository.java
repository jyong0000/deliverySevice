package com.deliveryservice.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deliveryservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Query("select o from Order o where o.user.email = :email order by o.orderDate desc")
	List<Order> findOrders(@Param("email") String email, Pageable pageable);
	
	@Query("select count(o) from Order o where o.user.email = :email")
	Long countOrder(@Param("email") String email);

}
