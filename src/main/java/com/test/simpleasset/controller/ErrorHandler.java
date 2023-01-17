package com.test.simpleasset.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.simpleasset.dto.error.ErrorResDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleValidation(final MethodArgumentNotValidException ex){
		final List<String> errors = new ArrayList<String>();
		
		ex.getBindingResult().getAllErrors().forEach(e->{
			errors.add(e.getDefaultMessage());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResDto<String>> handleValidation(final BadCredentialsException ex){
		final String error = "Salah Username atau Password";	
		final ErrorResDto<String> errorResDto = new ErrorResDto<>();
		errorResDto.setMessage(error);
		return new ResponseEntity<>(errorResDto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResDto<String>> handleValidation(final DataIntegrityViolationException ex){
		final String error = "Constraint Violation : mungkin duplikat kunci bisnis?";
		final ErrorResDto<String> errorResDto = new ErrorResDto<>();
		errorResDto.setMessage(error);
		return new ResponseEntity<>(errorResDto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResDto<String>> handleValidation(final AccessDeniedException ex){
		final String error = ex.getMessage();
		final ErrorResDto<String> errorResDto = new ErrorResDto<>();
		errorResDto.setMessage(error);
		return new ResponseEntity<>(errorResDto, HttpStatus.BAD_REQUEST);
	}
}