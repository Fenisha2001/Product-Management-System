package com.jsp.pms.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.pms.dto.ProductRequestDTO;
import com.jsp.pms.entity.Product;
import com.jsp.pms.service.ProductService;
import com.jsp.pms.utility.ErrorStructure;
import com.jsp.pms.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class ProductController {

	private ProductService productService;
	public ProductController(ProductService productService)
	{
		this.productService=productService;
	}

	//	@PostMapping("/products")
	//	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product)
	//	{
	//		return productService.saveProduct(product);
	//	}

	@Operation(description = "The endpoint is used to add a new product to the database", responses = {
			@ApiResponse(responseCode = "200", description = "Product saved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid inputs")
	}) 
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody ProductRequestDTO productRequest)
	{
		return productService.saveProduct(productRequest);
	}

	@Operation(description = "The endpoint is used to update the product details", responses = {
			@ApiResponse(responseCode = "200", description = "Product updated successfully"),
			@ApiResponse(responseCode = "404", description = "Product doesnot Exist",content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			})
	})
	@PutMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int productId,@RequestBody Product updatedProduct)
	{
		return productService.updateProduct(productId, updatedProduct);
	}

	//	@PutMapping("/products/{productId}")
	//	public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable int productId,@RequestBody ProductRequestDTO productRequest)
	//	{
	//		return productService.updateProduct(productId, productRequest);
	//	}

	@DeleteMapping("products/{productId}")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable int productId)
	{
		return productService.deleteProduct(productId);
	}

	@Operation(description = "The endpoint is used to fetch the product based on the ID", responses = {
			@ApiResponse(responseCode = "200", description = "Product Found"),
			@ApiResponse(responseCode = "404", description = "Product not found by given ID",content = {
					@Content(schema = @Schema(implementation = ErrorStructure.class))
			})
	})
	@GetMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int productId){

		return productService.findProductById(productId);
	}

	@Operation(description = "The endpoint is used to fetch the product")
	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct(){

		return productService.findAllProduct();
	}




}