package com.MicroServiceProject.ProductApplication.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {

	@NotBlank
	private String product_name;
	
	@NotBlank
	private String description;
	
	@NotNull
	private Double price;
	
	@NotNull
	private Integer quntity;
}
