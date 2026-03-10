package com.MicroServiceProject.ProductApplication.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroServiceProject.ProductApplication.DTO.ProductRequestDTO;
import com.MicroServiceProject.ProductApplication.DTO.ProductResponseDTO;
import com.MicroServiceProject.ProductApplication.Response.ApiResponse;
import com.MicroServiceProject.ProductApplication.Service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductContoller {

	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<ApiResponse<ProductResponseDTO>>insertProduct(@Valid @RequestBody ProductRequestDTO dto){
		
		return ResponseEntity.ok(productService.createProduct(dto));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<ProductResponseDTO>>>getAllProduct(){
		return ResponseEntity.ok(productService.getAllProduct());
	}
	
	@GetMapping("/{product_name}")
	public ResponseEntity<ApiResponse<ProductResponseDTO>>getProductByName(@PathVariable String product_name){
		return ResponseEntity.ok(productService.getProductByName(product_name));
	}
	
	@PutMapping("/{product_name}")
	public ResponseEntity<ApiResponse<ProductResponseDTO>>updateProduct(
			@Valid @RequestBody ProductRequestDTO dto, @PathVariable String product_name){
		
		return ResponseEntity.ok(productService.updateProduct(dto, product_name));
	}
	
	@DeleteMapping("/{product_name}")
	public ResponseEntity<ApiResponse<ProductResponseDTO>>deleteProduct(@PathVariable String name){
		return ResponseEntity.ok(productService.deleteProductByName(name));
	}
}
