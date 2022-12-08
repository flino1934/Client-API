package com.lino.desafio.resouce.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lino.desafio.service.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resouceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

		HttpStatus status =HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimeStamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resoruce Not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(err);

	}
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<StandardError> database(DataAccessException e, HttpServletRequest request){
		
		HttpStatus status =HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimeStamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database Excption");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(err);
		
	}

}
