package com.MicroServiceProject.OrderService.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDTO {

	@NotNull(message = "User ID is required")
	private Long userId;
	
	@NotNull(message = "Product ID is required")
	private Long productId;
	
	@NotNull(message = "Quantity is required")
	@Min(message ="Qunatity must be at least 1", value = 1)
	private Integer quantity;
	
	@NotNull(message = "Total Price is required")
	@Min(value = 1,message = "Total Price must be greater than 0")
	private Double totalPrice;
	
}
