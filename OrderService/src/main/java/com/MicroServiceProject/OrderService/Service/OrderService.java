package com.MicroServiceProject.OrderService.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.MicroServiceProject.OrderService.DTO.OrderRequestDTO;
import com.MicroServiceProject.OrderService.DTO.OrderResponseDTO;
import com.MicroServiceProject.OrderService.Entity.Order;
import com.MicroServiceProject.OrderService.Enum.OrderStatus;
import com.MicroServiceProject.OrderService.Repository.OrderRepository;
import com.MicroServiceProject.OrderService.Response.ApiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	
	public ApiResponse<OrderResponseDTO>createOrder(OrderRequestDTO dto){
		
		Order order=new Order();
		order.setOrderNumber("ORD-"+ UUID.randomUUID().toString().substring(0, 8).toUpperCase());
		order.setProductId(dto.getProductId());
		order.setUserId(dto.getUserId());
		order.setQuantity(dto.getQuantity());
		order.setTotalPrice(dto.getTotalPrice());
		order.setStatus(OrderStatus.PENDING);
		order.setOrderDate(LocalDateTime.now());
		order.setUpdatedAt(LocalDateTime.now());
		
		Order savedOrder=orderRepository.save(order);
		
		OrderResponseDTO response=new OrderResponseDTO(
				savedOrder.getOrderId(),
				savedOrder.getUserId(),
				savedOrder.getProductId(),
				savedOrder.getOrderNumber(),
				savedOrder.getQuantity(),
				savedOrder.getTotalPrice(),
				savedOrder.getStatus(),
				savedOrder.getOrderDate(),
				savedOrder.getUpdatedAt());
		
		return new ApiResponse<OrderResponseDTO>(
				true, 
				"Order Created Successfully",
				response);
	}
}
