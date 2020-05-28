package com.bridgelabz.notemicroservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;
@Data
@Entity
@Table(name = "Label_Information")
public class Label 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labelId;
	@NotBlank(message= "Name is mandatory")
	private String name;
    private long UserId;
}
