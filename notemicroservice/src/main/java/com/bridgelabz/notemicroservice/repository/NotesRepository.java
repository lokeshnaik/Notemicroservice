package com.bridgelabz.notemicroservice.repository;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.notemicroservice.entity.Notes;

public interface NotesRepository
{
	Notes save(Notes notesInformation);
	boolean deleteNotes(long Id, long userId);
	List<Notes> getPinnedNotes(long userid);
	List<Notes> getArchievedNotes(long userid);
	List<Notes> getTrashedNotes(long userid);
	boolean updateNotes(Notes information,long id);
	List <Notes> getAllNotes(long userid);
	List <Notes> getAllTrashedNotes(long userid);
	List <Notes> getAllArchievedNote(long userid);
	List <Notes> getallpinnednotes(long userid);
	Optional<Notes> findById(long id);
	Optional<Notes> getNoteById(Long noteid,Long userid);
}
