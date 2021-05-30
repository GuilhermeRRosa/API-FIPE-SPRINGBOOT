package com.vecon.controllers.exceptions;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vecon.services.exceptions.MethodArgumentNotValidExceptionHandler;
import com.vecon.services.exceptions.ObjectNotFoundException;
import com.vecon.services.exceptions.StandardError;

@RestControllerAdvice
public class Handler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<?> objectNotFound(ObjectNotFoundException e){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), Calendar.getInstance().getTime());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> objectNotFound(MethodArgumentNotValidException e){
		List<FieldError> errors= e.getBindingResult().getFieldErrors();
		String fields = errors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String causes = errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));	
		MethodArgumentNotValidExceptionHandler err = new MethodArgumentNotValidExceptionHandler(HttpStatus.BAD_REQUEST.value(), "Validation error", Calendar.getInstance().getTime(), fields, causes);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> objectNotFound(DataIntegrityViolationException e){
		String error = "Campo CPF e/ou Email contém dados duplicados no sistema";
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), error, Calendar.getInstance().getTime());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
