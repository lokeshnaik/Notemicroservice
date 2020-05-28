package com.bridgelabz.notemicroservice.response;
import lombok.Data;
@Data
public class NotesResponse
{
	public NotesResponse() {

	}
  String message;
	int statusCode;
	Object data;

	public NotesResponse(String message, int statusCode, Object data) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
	}
}
