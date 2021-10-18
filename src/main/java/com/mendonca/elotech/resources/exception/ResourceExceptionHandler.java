package com.mendonca.elotech.resources.exception;

import javax.servlet.http.HttpServletRequest;

import com.mendonca.elotech.service.exceptions.AuthorizationException;
import com.mendonca.elotech.service.exceptions.DataIntegrityException;
import com.mendonca.elotech.service.exceptions.FileException;
import com.mendonca.elotech.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<com.mendonca.elotech.resources.exception.StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		com.mendonca.elotech.resources.exception.StandardError err = new com.mendonca.elotech.resources.exception.StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<com.mendonca.elotech.resources.exception.StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		
		com.mendonca.elotech.resources.exception.StandardError err = new com.mendonca.elotech.resources.exception.StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<com.mendonca.elotech.resources.exception.StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		com.mendonca.elotech.resources.exception.ValidationError err = new com.mendonca.elotech.resources.exception.ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", e.getMessage(), request.getRequestURI());
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<com.mendonca.elotech.resources.exception.StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		
		com.mendonca.elotech.resources.exception.StandardError err = new com.mendonca.elotech.resources.exception.StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<com.mendonca.elotech.resources.exception.StandardError> file(FileException e, HttpServletRequest request) {
		
		com.mendonca.elotech.resources.exception.StandardError err = new com.mendonca.elotech.resources.exception.StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de arquivo", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
