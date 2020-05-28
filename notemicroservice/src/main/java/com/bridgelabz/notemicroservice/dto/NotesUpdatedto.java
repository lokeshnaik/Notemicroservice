package com.bridgelabz.notemicroservice.dto;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
public class NotesUpdatedto 
{
	@NotBlank(message="Id is mandatory")
	private Long id;
	@NotBlank(message="Title is mandatory")
	private String title;
	@NotBlank(message="description is mandatory")
	private String description;
	private boolean isArchieved;
	private boolean isPinned;
	private boolean isTrashed;
	private LocalDateTime createdDateAndTime;
	private LocalDateTime upDateAndTime;
}
