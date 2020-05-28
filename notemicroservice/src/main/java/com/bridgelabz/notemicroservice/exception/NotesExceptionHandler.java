package com.bridgelabz.notemicroservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.notemicroservice.response.ExceptionResponse;
@ControllerAdvice
public class NotesExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotesException.class)
	public final ResponseEntity<ExceptionResponse> notesException(NotesException exc) 
	{	
		ExceptionResponse exception = new ExceptionResponse();
		exception.setMessage(exc.getMessage());
		exception.setCode(exc.getStatus());
		exception.setTime(LocalDateTime.now());
        return ResponseEntity.status(exception.getCode()).body(new ExceptionResponse(exception.getMessage(), exception.getCode(),exception.getTime()));
	}

}
