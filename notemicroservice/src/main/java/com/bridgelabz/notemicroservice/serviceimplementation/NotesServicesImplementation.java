package com.bridgelabz.notemicroservice.serviceimplementation;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.notemicroservice.dto.NotesInformationdto;
import com.bridgelabz.notemicroservice.entity.Notes;
import com.bridgelabz.notemicroservice.exception.NotesException;
import com.bridgelabz.notemicroservice.repository.NotesRepository;
import com.bridgelabz.notemicroservice.response.Response;
import com.bridgelabz.notemicroservice.service.NotesServices;
import com.bridgelabz.notemicroservice.utility.JWTOperations;

@Service
public class NotesServicesImplementation implements NotesServices
{ 
	@Autowired
	JWTOperations jwtop;
     @Autowired
      NotesRepository notesRepository;
     
     @Autowired
     NotesServicesImplementation notesServicesImplementation;
     
     @PersistenceContext
     EntityManager entitymanager;
     @Autowired
 	private RestTemplate restTemplate;
 	public Response isUserExists(Long id)
 	{
 		Response response=restTemplate.getForEntity("http://localhost:8097/user/getuserbyid/"+id,Response.class).getBody();
 		return response;
 	}
    
  
	@Override
    @Transactional
	public Notes addNotes(NotesInformationdto notesInformationdto, String token)
	{
		Long userId =(long )0;
		  Notes notesInformation=new Notes();
		  userId =(long )jwtop.parseJWT(token);
		  isUserExists(userId);
		  BeanUtils.copyProperties(notesInformationdto, notesInformation);
		  notesInformation.setUserId(userId);
		  notesInformation.setDataAndTimeCreated(LocalDateTime.now());
		  Notes notes=notesRepository.save(notesInformation);  
		 return notes;
	}
	
	@Override
	@Transactional
	public Notes deleteNotes(Long notesId, String token) throws  NotesException
	{
		Long userId = (Long) jwtop.parseJWT(token);
		
		isUserExists(userId);
	        List<Notes> notes= getallNotes(token);
	        //Notes note=new Notes();
	        int flag=0;
	        for(Notes note:notes)
	        {
	        	if(note.getNoteId()==notesId)
	        	{
	        		note.setTrashed(true);
	              	note.setArchieved(false);
	              	note.setPinned(false);
	        		flag++;
	        		return note;
	        	}
	        }
	        if(flag>0)
	        {
	        	Long note1=notesId;
	        }
	        else
	        {
	        	new NotesException("Note is not found", HttpStatus.NOT_FOUND);	
	        }
      
		return null;
	}
	
	public Notes getNoteById(Long noteid,Long userid) throws NotesException
	{
		return notesRepository.getNoteById(noteid, userid).orElseThrow(() -> new NotesException("no notes in the list",HttpStatus.OK));
	}
	


	@Transactional
	@Override
	public Notes pinNote(Long notesId, String token) throws NotesException {
		Long userId = (Long) jwtop.parseJWT(token);
		isUserExists(userId);
		 List<Notes> notes= getallNotes(token);
	        int flag=0;
	        for(Notes note:notes)
	        {
	        	if(note.getNoteId()==notesId)
	        	{
	        		note.setPinned(true);
	        		note.setArchieved(false);
	        		note.setTrashed(false);
	        		flag++;
	        		notesRepository.save(note);
	        		return note;
	        	}
	        }
	        if(flag>0)
	        {
	        	Long note1=notesId;
	        }
	        else
	        {
	        	new NotesException("Note is not found", HttpStatus.NOT_FOUND);	
	        }

		return null;
	}
	
	@Transactional
	@Override
	public Notes archieveNotes(long notesId, String token) throws  NotesException 
	{
		Long userId = (Long) jwtop.parseJWT(token);
		isUserExists(userId);
		 List<Notes> notes= getallNotes(token);
	        //Notes note=new Notes();
	        int flag=0;
	        for(Notes note:notes)
	        {
	        	if(note.getNoteId()==notesId)
	        	{
	        		note.setPinned(false);
	        		note.setArchieved(true);
	        		note.setTrashed(false);
	        		flag++;
	        		notesRepository.save(note);
	        		return note;
	        	}
	        }
	        if(flag>0)
	        {
	        	Long note1=notesId;
	        }
	        else
	        {
	        	new NotesException("Note is not found", HttpStatus.NOT_FOUND);	
	        }

		return null;
	}
	@Transactional
	@Override
	public Notes trashNotes(long notesId, String token) throws  NotesException 
	{
		Long userId = (Long) jwtop.parseJWT(token);
		isUserExists(userId);	
		 List<Notes> notes= getallNotes(token);
	        //Notes note=new Notes();
	        int flag=0;
	        for(Notes note:notes)
	        {
	        	if(note.getNoteId()==notesId)
	        	{
	        		note.setPinned(false);
	        		note.setArchieved(false);
	        		note.setTrashed(true);
	        		flag++;
	        		notesRepository.save(note);
	        		return note;
	        	}
	        }
	        if(flag>0)
	        {
	        	Long note1=notesId;
	        }
	        else
	        {
	        	new NotesException("Note is not found", HttpStatus.NOT_FOUND);	
	        }

		return null;
	}
	
	@Transactional
	@Override
	public Notes updateNotes(Notes information,long noteId, String token) throws  NotesException 
	{
		Long userId = (long) 0;
		userId = (Long) jwtop.parseJWT(token);
		isUserExists(userId);
		 List<Notes> notes= getallNotes(token);
	        int flag=0;
	        for(Notes note:notes)
	        {
	        	if(note.getNoteId()==noteId)
	        	{
	        		flag++;
	        		note.setTitle(information.getTitle());
	        		note.setDescription(information.getDescription());
	        		note.setPinned(information.isPinned());
	        		note.setTrashed(information.isTrashed());
	        		note.setArchieved(information.isArchieved());
	        		note.setUpDateAndTime(LocalDateTime.now());
	        		notesRepository.save(note);
	        		return note;
	        	}
	        }
	        if(flag>0)
	        {
	        	Long note1=noteId;
	        }
	        else
	        {
	        	new NotesException("Note is not found", HttpStatus.NOT_FOUND);	
	        }
	        	
     	return null;
	}
	@Transactional
	@Override
	public List<Notes> getallNotes(String token) 
	{
		
		Long userId=(Long) jwtop.parseJWT(token);
		List<Notes> notesList=notesRepository.getAllNotes(userId);
		return notesList;
	}
	

	@Transactional
	@Override
	public List<Notes> getalltrashed(String token) 
	{
		
		Long userId=(Long) jwtop.parseJWT(token);
		List<Notes> notesList=notesRepository.getAllTrashedNotes(userId);
		return notesList;
		
	}
	@Transactional
	@Override
	public List<Notes> getallachieve(String token) 
	{	
		Long userId=(Long) jwtop.parseJWT(token);
		List<Notes> notesList=notesRepository.getAllArchievedNote(userId);
		return notesList;	
	}
	
	@Transactional
	@Override
	public List<Notes> getAllPinnedNotes(String token) 
	{	
		Long userId=(Long) jwtop.parseJWT(token);
		List<Notes> notesList=notesRepository.getallpinnednotes(userId);
		return notesList;
		
	}
	
	@Override
	public List<Notes> getAllNotesBySorted(String token)
	{
		List<Notes> noteList = notesServicesImplementation.getallNotes(token);
		List<Notes> sortedNoteList = noteList.stream().sorted(Comparator.comparing(Notes::getTitle)).collect(Collectors.toList());	
		return sortedNoteList;
	}

}
