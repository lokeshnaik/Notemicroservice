package com.bridgelabz.notemicroservice.repository;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.notemicroservice.entity.Label;
public interface LabelRepository 
{
	Label save(Label information);
	Optional<Label> fetchLabelById(Long id);
	int deleteLabelpermanently(Long id);
	List<Label> getAllLabel(Long id);
}
