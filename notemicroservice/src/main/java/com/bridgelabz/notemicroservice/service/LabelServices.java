package com.bridgelabz.notemicroservice.service;

import java.util.List;

import com.bridgelabz.notemicroservice.dto.LabelUpdate;
import com.bridgelabz.notemicroservice.dto.Labeldto;
import com.bridgelabz.notemicroservice.entity.Label;
import com.bridgelabz.notemicroservice.entity.Notes;
import com.bridgelabz.notemicroservice.exception.LabelException;
import com.bridgelabz.notemicroservice.exception.NotesException;
public interface LabelServices
{
	Label addLabel(Labeldto labeldto, String token);
	void editLabel(LabelUpdate label, String token) throws LabelException;
	Label deleteLabel(LabelUpdate label, String token) throws  NotesException, LabelException;
	List<Label> getLabel(String token);
	Notes maplabeltonote(Labeldto labeldto, String token, long id) throws LabelException, NotesException;
	Notes addingLabelToNote(long labelId, String token, long noteId) throws NotesException, LabelException;
	Notes removingLabelFromNote(long labelId, String token, long noteId) throws NotesException, LabelException;
}
