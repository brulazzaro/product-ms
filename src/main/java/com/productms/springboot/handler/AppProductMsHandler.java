package com.productms.springboot.handler;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.productms.springboot.model.response.ErrorMessage;

@ControllerAdvice
public class AppProductMsHandler {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Void> handleMessageNotReadable(final HttpMessageNotReadableException ex) {
		final ErrorMessage errorMsg = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity(errorMsg, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> handleEntityNotFound(final EntityNotFoundException ex) {
		final ErrorMessage errorMsg = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity(errorMsg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotWritableException.class)
	public ResponseEntity<Void> handleMessageNotWritable(final HttpMessageNotWritableException ex) {
		final ErrorMessage errorMsg = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity(errorMsg, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Void> handleTypeMismatch(final MethodArgumentTypeMismatchException ex) {
		final ErrorMessage errorMsg = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity(errorMsg, HttpStatus.BAD_REQUEST);
	}
	
}
