package com.deliveryservice.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.deliveryservice.constant.OrderStatus;
import com.deliveryservice.entity.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderHistDto {

	public OrderHistDto(Order order) {
		this.orderId = order.getId();
		this.orderDate = order.getOrderDate()
						.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.orderStatus = order.getOrderStatus();
	}
	
	private Long orderId;
	private String orderDate;
	private OrderStatus orderStatus;
	private List<OrderMenuDto> orderMenuDtoList = new ArrayList<>();
	
	public void addOrderMenuDto(OrderMenuDto orderMenuDto) {
		this.orderMenuDtoList.add(orderMenuDto);
	}
}
