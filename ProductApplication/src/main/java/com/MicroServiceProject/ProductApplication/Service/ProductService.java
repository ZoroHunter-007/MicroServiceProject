package com.MicroServiceProject.ProductApplication.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.MicroServiceProject.ProductApplication.DTO.ProductRequestDTO;
import com.MicroServiceProject.ProductApplication.DTO.ProductResponseDTO;
import com.MicroServiceProject.ProductApplication.Entity.Product;
import com.MicroServiceProject.ProductApplication.Exception.ResourceNotFoundException;
import com.MicroServiceProject.ProductApplication.Repository.ProductRepository;
import com.MicroServiceProject.ProductApplication.Response.ApiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
	//Insert Product in DB 
	public ApiResponse<ProductResponseDTO>createProduct(ProductRequestDTO dto){
		
		Product product=new Product();
		product.setProductName(dto.getProduct_name());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setQuntity(dto.getQuntity());
		
		Product savedProduct=productRepository.save(product);
		
		ProductResponseDTO responseDTO=new ProductResponseDTO(
				savedProduct.getProductId(),
				savedProduct.getProductName(), 
				savedProduct.getDescription(), 
				savedProduct.getPrice(),
				savedProduct.getQuntity());
		
		return new ApiResponse<ProductResponseDTO>(true, 
				"Product Inserted Successfully", 
				responseDTO);
	}
	
	//get all product from db
	public ApiResponse<List<ProductResponseDTO>>getAllProduct(){
		List<ProductResponseDTO> products=productRepository.findAll()
				.stream()
				.map(product-> new ProductResponseDTO(
						product.getProductId(), 
						product.getProductName(), 
						product.getDescription(), 
						product.getPrice(), 
						product.getQuntity())
						)
				.collect(Collectors.toList());
		
		return new ApiResponse<List<ProductResponseDTO>>(
				true,
				"All Products Fetched Successfully"
				,products);
	}
	
	public ApiResponse<ProductResponseDTO>getProductByName(String product_name){
		Product product=productRepository.findByproductName(product_name).
				orElseThrow(()-> 
				new ResourceNotFoundException("Product Not Found"));
		ProductResponseDTO responseDTO=new ProductResponseDTO(
				product.getProductId(), 
				product.getProductName(), 
				product.getDescription(), 
				product.getPrice(), 
				product.getQuntity());
		
		return new ApiResponse<ProductResponseDTO>(
				true, 
				"Product "+product_name+" Fetch Successfully",
				responseDTO);
	}
	
	public ApiResponse<ProductResponseDTO>updateProduct(ProductRequestDTO dto,String product_name){
		Product product=productRepository.findByproductName(product_name).
				orElseThrow(()->
				new ResourceNotFoundException("Product Not Found"));
		
		product.setProductName(dto.getProduct_name());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setQuntity(dto.getQuntity());
		
		Product savedProduct=productRepository.save(product);
		
		ProductResponseDTO responseDTO=new ProductResponseDTO(
				savedProduct.getProductId(),
				savedProduct.getProductName(),
				savedProduct.getDescription(), 
				savedProduct.getPrice(), 
				savedProduct.getQuntity());
		return new ApiResponse<ProductResponseDTO>(
				true, 
				"Product "+product_name+ " Updated Successfully",
				responseDTO);
		
		
	}
	
	public ApiResponse<ProductResponseDTO>deleteProductByName(String name){
		
		Product product=productRepository.findByproductName(name).
				orElseThrow(()->
				new ResourceNotFoundException("Product Not Found"));
		
		productRepository.delete(product);
		
		return new  ApiResponse<ProductResponseDTO>(true,
				"Product "+name+" Deleted Successfuly",
				null);
	}
}
