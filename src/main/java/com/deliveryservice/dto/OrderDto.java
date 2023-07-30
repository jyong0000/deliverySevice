package com.deliveryservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
	
	@NotNull(message = "메뉴 아이디는 필수 입력입니다.")
	private Long menuId;
	
	@Min(value = 1, message = "최소 주문수량은 1개 입니다.")
	@Max(value = 99, message = "최대 주문수량은 99개 입니다.")
	
	private int count;
}
