package com.jsp.pms.serviceImpl;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.pms.dto.ProductRequestDTO;
import com.jsp.pms.entity.Product;
import com.jsp.pms.exception.ProductNotFoundByIdException;
import com.jsp.pms.exception.ProductNotFoundException;
import com.jsp.pms.repo.ProductRepo;
import com.jsp.pms.service.ProductService;
import com.jsp.pms.utility.ResponseStructure;
@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepo productRepository;
	private ResponseStructure<Product> responseStructure;
	private ResponseStructure<List<Product>> structure;

	public ProductServiceImpl(ProductRepo productRepo, ResponseStructure<Product> responseStructure,ResponseStructure<List<Product>> structure) {
		super();
		this.productRepository = productRepo;
		this.responseStructure = responseStructure;
		this.structure=structure;
	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> saveProduct(ProductRequestDTO productRequest) {
		//mapping DTO to entity
		Product product = productRepository.save(mapToProduct(productRequest, new Product()));
		return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
				.setMessage("Product saved successfully")
				.setData(product));
	}

	//	@Override
	//	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
	//		Product exProduct=productRepository.save(product);
	//		return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
	//				.setMessage("Object saved")
	//				.setData(exProduct));
	//
	//	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int productId,Product updatedProduct) {
		return productRepository.findById(productId).map(exProduct->{
			updatedProduct.setProductId(exProduct.getProductId());
			exProduct=productRepository.save(updatedProduct);
			return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
					.setMessage("Object updated successfully")
					.setData(exProduct));
		}).orElseThrow(()->new ProductNotFoundByIdException("Invalid productId"));
	}

	//	@Override
	//	public ResponseEntity<ResponseStructure<Product>> updateProduct(int productId, ProductRequestDTO productRequest) {
	//		Product product = mapToProduct(productRequest,new Product())
	//		return productRepository.findById(productId)
	//				.map(exProduct -> {
	//					product.setProductId(exProduct.getProductId());
	//					exProduct = productRepository.save(product);
	//					return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.OK.value())
	//							.setMessage("Product Updated..")
	//							.setData(exProduct));
	//				}).orElseThrow(()->new ProductNotFoundByIdException("Product Not Found.."));
	//	}	

	@Override
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int productId) {
		return productRepository.findById(productId).map(exProduct->{
			productRepository.delete(exProduct);
			return ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
					.setMessage("Object deleted successfully")
					.setData(exProduct));
		}).orElseThrow(()->new ProductNotFoundByIdException("Invalid productId"));

	}
	
	@Override
	public ResponseEntity<ResponseStructure<Product>> findProductById(int productId) {
		return productRepository.findById(productId).map(product->
		ResponseEntity.ok(responseStructure.setStatuscode(HttpStatus.OK.value())
				.setMessage("product found")
				.setData(product)))
				.orElseThrow(()->new ProductNotFoundByIdException("Invalid productId"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct() {
		List<Product> products=productRepository.findAll();
		if(!products.isEmpty()) {
			return ResponseEntity.ok(structure.setStatuscode(HttpStatus.OK.value())
					.setMessage("product found")
					.setData(products));
		}else throw new ProductNotFoundException("No products exists");

	}

	private Product mapToProduct(ProductRequestDTO productRequest, Product product) {
		product.setProductName(productRequest.getProductName());
		product.setProductBrand(productRequest.getProductBrand());
		product.setProductPrice(productRequest.getProductPrice());
		return product;
	}

	//	private Product mapToProduct(ProductRequestDTO productdto) {
	//		   return Product.builder()
	//				   .ProductName(ProductRequest.getProductName())
	//				   .ProductBrand(ProductRequest.getProductBrand())
	//				   .ProductPrice(ProductRequest.getProductPrice())
	//				   .build();	   
	//			}

}
