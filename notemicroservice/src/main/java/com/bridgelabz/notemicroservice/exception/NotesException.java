package com.bridgelabz.notemicroservice.exception;

import org.springframework.http.HttpStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@ToString

public class NotesException extends Exception
{
	private static final long serialVersionUID = 1L;
	HttpStatus status;
	private String message;
	
	public NotesException(String message,HttpStatus status) 
	{
		this.message = message;
		this.status=status;
	}	
}
