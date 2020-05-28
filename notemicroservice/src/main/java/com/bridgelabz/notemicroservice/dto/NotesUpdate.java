package com.bridgelabz.notemicroservice.dto;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesUpdate
{
  @NotBlank(message="Title is mandatory")
   String title;
  @NotBlank(message="Description is mandatory")
   String description;   
}
