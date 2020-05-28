package com.bridgelabz.notemicroservice.dto;
import javax.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LabelUpdate 
{
	@NotBlank(message="LabelId is mandantory")
	private Long labelId;
	@NotBlank(message="LabelName is mandantory")
	private String labelName;
}
