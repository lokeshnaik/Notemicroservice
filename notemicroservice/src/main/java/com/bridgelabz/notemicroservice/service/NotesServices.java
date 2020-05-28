package com.bridgelabz.notemicroservice.service;

import java.util.List;

import com.bridgelabz.notemicroservice.dto.NotesInformationdto;
import com.bridgelabz.notemicroservice.entity.Notes;
import com.bridgelabz.notemicroservice.exception.NotesException;
public interface NotesServices
{
	Notes addNotes(NotesInformationdto notesInformationdto,String token);
	Notes deleteNotes(Long notesId, String token)  throws  NotesException;
	Notes pinNote(Long notesId, String token)  throws  NotesException;
	Notes archieveNotes(long notesId, String token)  throws NotesException;
	Notes trashNotes(long notesId, String token)  throws  NotesException;
	Notes updateNotes(Notes information,long notesId,String token) throws  NotesException;
	List<Notes> getallNotes( String token);
	List<Notes> getalltrashed( String token);
	List<Notes> getallachieve( String token);
	List<Notes> getAllPinnedNotes( String token);
	List<Notes> getAllNotesBySorted( String token);
	
}
