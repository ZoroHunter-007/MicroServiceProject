package com.MicroServiceProject.OrderService.Entity;

import java.time.LocalDateTime;

import com.MicroServiceProject.OrderService.Enum.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@Column(unique = true,nullable = false)
	private String orderNumber;
	
	@Column(nullable = false)
	private Long productId;
	
	@Column(nullable = false)
	private Long userId;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private Double totalPrice;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	private LocalDateTime orderDate;
	private LocalDateTime updatedAt;
}
