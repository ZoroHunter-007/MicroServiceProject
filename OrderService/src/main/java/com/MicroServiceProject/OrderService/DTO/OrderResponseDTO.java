package com.MicroServiceProject.OrderService.DTO;

import java.time.LocalDateTime;

import com.MicroServiceProject.OrderService.Enum.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

	private Long orderId;
    private Long userId;
    private Long productId;
    private String orderNumber;
    private Integer quantity;
    private Double totalPrice;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private LocalDateTime updatedAt;
}
