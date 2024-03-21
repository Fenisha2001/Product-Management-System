package com.jsp.pms.utility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.pms.exception.ProductNotFoundByIdException;
import com.jsp.pms.exception.ProductNotFoundException;

@RestControllerAdvice
public class ApplicationHandler {

	private ErrorStructure<String> errorStructure;

	public ApplicationHandler(ErrorStructure<String> errorStructure) {
		super();
		this.errorStructure = errorStructure;
	}

	@ExceptionHandler(ProductNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> productNotFoundByIdException(ProductNotFoundByIdException ex)
	{
		return ResponseEntity.ok(errorStructure.setErrorStatuscode(HttpStatus.NOT_FOUND.value())
				.setErrorMessage(ex.getMessage())
				.setErrorData("product Object With the Given id doesnot exist"));
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> productNotFoundException(ProductNotFoundException ex)
	{
		return ResponseEntity.ok(errorStructure.setErrorStatuscode(HttpStatus.NOT_FOUND.value())
				.setErrorMessage(ex.getMessage())
				.setErrorData("product doesnot exists"));
	}

	//	// Iterating Using Map
	//			Map<String , String> messages = new HashMap<>();
	//
	////			errors.forEach(error -> {
	////				FieldError fieldError = (FieldError) error;
	////				messages.put(fieldError.getField(), fieldError.getDefaultMessage());
	////				
	////				messages.put(((FieldError) error).getField(), error.getDefaultMessage());
	////			});
	////			
	//			ex.getAllErrors().forEach(error -> {
	//				messages.put(((FieldError) error).getField(), error.getDefaultMessage());
	//			});
}