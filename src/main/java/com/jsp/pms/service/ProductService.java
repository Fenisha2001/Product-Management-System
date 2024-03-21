package com.jsp.pms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.pms.dto.ProductRequestDTO;
import com.jsp.pms.entity.Product;
import com.jsp.pms.utility.ResponseStructure;

public interface ProductService {
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(ProductRequestDTO productRequest);

//	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product);

	public ResponseEntity<ResponseStructure<Product>> updateProduct(int productId, Product product);
	
//	public ResponseEntity<ResponseStructure<Product>> updateProduct(int productId, ProductRequestDTO productRequest);

	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int productId);

	public ResponseEntity<ResponseStructure<Product>> findProductById(int productId);

	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct();
	


}
