package com.MicroServiceProject.OrderService.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroServiceProject.OrderService.DTO.OrderRequestDTO;
import com.MicroServiceProject.OrderService.DTO.OrderResponseDTO;
import com.MicroServiceProject.OrderService.Response.ApiResponse;
import com.MicroServiceProject.OrderService.Service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<OrderResponseDTO>>createOrder(@Valid @RequestBody OrderRequestDTO dto){
		return ResponseEntity.ok(orderService.createOrder(dto));
	}
}
