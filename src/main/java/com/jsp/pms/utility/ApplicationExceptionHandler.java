package com.jsp.pms.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	private ErrorStruc errorStruc;

	public ApplicationExceptionHandler(ErrorStruc errorStruc) {
		super();
		this.errorStruc = errorStruc;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> errors = ex.getAllErrors();
		List<String> messages = new ArrayList<String>();
		errors.forEach(error -> {
			String message = error.getDefaultMessage();
			messages.add(message);
		});
		return ResponseEntity.badRequest().body(
				errorStruc.setStatuscode(HttpStatus.BAD_REQUEST.value())
				.setMessage("Invalid inputs")
				.setRootCause(messages));


	}


}
