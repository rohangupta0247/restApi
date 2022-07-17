package com.saptris.restApi.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.saptris.restApi.controller.ResponseStructure;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> genericExceptionHandler(Exception ex){
		String message=ex.getClass()+":"+ex.getMessage()+"\nat "+String.join("\nat ",
				Arrays.asList(ex.getStackTrace()).stream().map(Object::toString).toArray(String[]::new));
		return new ResponseEntity<String>(message,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(MissingDataException.class)
	public ResponseEntity<ResponseStructure> missingDataExceptionHandler(MissingDataException ex){
		return ResponseStructure.userError(ex.getMessage());
	}
	@ExceptionHandler(AppException.class)
	public ResponseEntity<ResponseStructure> appExceptionHandler(AppException ex){
		return ResponseStructure.internalServerError(ex.getMessage());
	}
	@ExceptionHandler(MethodArgumentNotValidException.class) //for dto valiation exception
	public ResponseEntity<ResponseStructure> dtoValiationExceptionHandler(MethodArgumentNotValidException ex){
		String message=ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
		return ResponseStructure.userError(message);
	}
	@ExceptionHandler(ConstraintViolationException.class) //for pathParam validation exception
	public ResponseEntity<ResponseStructure> pathParamValidationExceptionHandler(ConstraintViolationException ex){
		String message=ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
		return ResponseStructure.userError(message);
	}
}
