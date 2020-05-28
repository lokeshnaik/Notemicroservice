package com.bridgelabz.notemicroservice.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class NotesInformationdto 
{
	@NotBlank(message = "Title is mandatory")
   private String title;
	@NotBlank(message = "Desciption is mandatory")
    private String description;  
}
