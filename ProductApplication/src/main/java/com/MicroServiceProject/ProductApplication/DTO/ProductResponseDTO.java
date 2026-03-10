package com.MicroServiceProject.ProductApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDTO {

	private Long product_id;
	private String product_name;
	private String description;
	private Double price;
	private Integer quntity;
}
