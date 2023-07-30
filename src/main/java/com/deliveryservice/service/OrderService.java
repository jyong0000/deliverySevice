package com.deliveryservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.deliveryservice.dto.OrderDto;
import com.deliveryservice.dto.OrderHistDto;
import com.deliveryservice.dto.OrderMenuDto;
import com.deliveryservice.entity.Menu;
import com.deliveryservice.entity.MenuImg;
import com.deliveryservice.entity.Order;
import com.deliveryservice.entity.OrderMenu;
import com.deliveryservice.entity.User;
import com.deliveryservice.repository.MenuImgRepository;
import com.deliveryservice.repository.MenuRepository;
import com.deliveryservice.repository.OrderRepository;
import com.deliveryservice.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
	private final MenuRepository menuRepository;
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final MenuImgRepository menuImgRepository;
	
	//주문
	public Long order(OrderDto orderDto, String email) {
		
		Menu menu = menuRepository.findById(orderDto.getMenuId())
				.orElseThrow(EntityNotFoundException::new);
		
		
		User user = userRepository.findByEmail(email);
		
		
		List<OrderMenu> orderMenuList = new ArrayList<>();
		OrderMenu orderMenu = OrderMenu.createOrderMenu(menu, orderDto.getCount());
		orderMenuList.add(orderMenu);
		
		Order order = Order.createOrder(user, orderMenuList);
		orderRepository.save(order);
		
		return order.getId();
	}
	
	//목록 가져오기
	@Transactional(readOnly = true)
	public Page<OrderHistDto> getOrderList(String email, Pageable pageable){
		List<Order> orders = orderRepository.findOrders(email, pageable);
		
		Long totalCount = orderRepository.countOrder(email);
		List<OrderHistDto> orderHistDtos = new ArrayList<>();
		
		for(Order order : orders) {
			OrderHistDto orderHistDto = new OrderHistDto(order);
			List<OrderMenu> orderMenus = order.getOrderMenus();
			
			for(OrderMenu orderMenu : orderMenus) {
				MenuImg menuImg = menuImgRepository.findByMenuIdAndRepimgYn(orderMenu.getMenu().getId(), "Y");
				OrderMenuDto orderMenuDto = new OrderMenuDto(orderMenu, menuImg.getMenuImgUrl());
				orderHistDto.addOrderMenuDto(orderMenuDto);
			}
			orderHistDtos.add(orderHistDto);
		}
		return new PageImpl<OrderHistDto>(orderHistDtos, pageable, totalCount);
	}
	
	//본인인증
	@Transactional(readOnly = true)
	public boolean validateOrder(Long orderId,String email) {
		User curUser = userRepository.findByEmail(email);
		Order order = orderRepository.findById(orderId)
				.orElseThrow(EntityNotFoundException::new);
		
		User savedUser = order.getUser();
		
		if(!StringUtils.equals(curUser.getEmail(), savedUser.getEmail())) {
			return false;
		}
		
		return true;
	}
	//취소
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
				 .orElseThrow(EntityNotFoundException::new);
		
		order.cancelOrder();
	}
	
	//삭제
	
	public void deleteOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
				 .orElseThrow(EntityNotFoundException::new);
		
		orderRepository.delete(order);
	}
}

