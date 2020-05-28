package com.bridgelabz.notemicroservice.serviceimplementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.notemicroservice.dto.LabelUpdate;
import com.bridgelabz.notemicroservice.dto.Labeldto;
import com.bridgelabz.notemicroservice.entity.Label;
import com.bridgelabz.notemicroservice.entity.Notes;
import com.bridgelabz.notemicroservice.exception.LabelException;
import com.bridgelabz.notemicroservice.exception.NotesException;
import com.bridgelabz.notemicroservice.repository.LabelRepository;
import com.bridgelabz.notemicroservice.repository.NotesRepository;
import com.bridgelabz.notemicroservice.response.Response;
import com.bridgelabz.notemicroservice.service.LabelServices;
import com.bridgelabz.notemicroservice.utility.JWTOperations;
@Service
public class LabelServicesImplementation implements LabelServices
{
	

	@Autowired
	private JWTOperations jwtop;
	@Autowired
	private LabelRepository labelRepository;
	@Autowired
	private NotesRepository notesrepository;
	@Autowired
	private RestTemplate restTemplate;
	 @PersistenceContext
     EntityManager entitymanager;
	public Response isUserExists(Long id)
	{
		Response response=restTemplate.getForEntity("http://localhost:8097/user/getuserbyid/"+id,Response.class).getBody();
		return response;
	}
    @Transactional
	@Override
	public Label addLabel(Labeldto labeldto, String token)
	{
		Long userId = null;

		 userId = (Long) jwtop.parseJWT(token);
		Label labeladd = new Label();
		isUserExists(userId);
		BeanUtils.copyProperties(labeldto, labeladd);
		labeladd.setUserId(userId);
		labelRepository.save(labeladd);
		return labeladd;
	}
	
	
	@Transactional
	@Override
	public void editLabel(LabelUpdate labelupdate, String token) throws LabelException {
		Long id = null;
		id = (Long) jwtop.parseJWT(token);
		isUserExists(id);
	    Label label = labelRepository.fetchLabelById(labelupdate.getLabelId()).orElseThrow(() -> new LabelException("Label is not found", HttpStatus.NOT_FOUND));
        label.setName(labelupdate.getLabelName());
		labelRepository.save(label);

	}
	@Transactional
	@Override
	public Label deleteLabel(LabelUpdate labelupdate, String token) throws NotesException,LabelException {
		Long id = null;
		Label deletelabel = new Label();
		id = (Long) jwtop.parseJWT(token);
		isUserExists(id);
		deletelabel.setUserId(id);
		//Label labels = user.getLabel().stream().filter((label) -> label.getLabelId().equals(labelupdate.getLabelId())).findFirst().orElseThrow(() -> new LabelException("label not found", HttpStatus.NOT_FOUND));;
		labelRepository.deleteLabelpermanently(deletelabel.getLabelId());
		//user.getLabel().remove(labels);
      return deletelabel;
	}
	
	@Override
	@Transactional
	public List<Label> getLabel(String token) {
		Long userId = null;
		userId = (Long) jwtop.parseJWT(token);	
		List<Label> labels = labelRepository.getAllLabel(userId);
		return labels;
	}
	
	@Transactional
	@Override
	public Notes maplabeltonote(Labeldto labeldto, String token, long noteId)	throws LabelException, NotesException
	{
		Label label = new Label();
		Long userId = null;
		userId = (Long) jwtop.parseJWT(token);
		isUserExists(userId);
		Notes note = notesrepository.findById(noteId).orElseThrow(() -> new NotesException("Note not found", HttpStatus.NOT_FOUND));
		BeanUtils.copyProperties(labeldto, label);
		label.setUserId(userId);
		labelRepository.save(label);
		note.getLabel().add(label);
		notesrepository.save(note);
       return note;
	}
	
	@Transactional
	@Override
	public Notes addingLabelToNote(long labelId, String token, long noteId)	throws NotesException, LabelException
	{
		Long userId = null;
		 Notes note=new Notes();
		 Label labels=new Label();
		userId = (Long) jwtop.parseJWT(token);
		isUserExists(userId);
		note.setUserId(userId);
		labels.setUserId(userId);
		Notes notes = notesrepository.findById(noteId).orElseThrow(() -> new NotesException("Note is not found", HttpStatus.NOT_FOUND));
		Label label = labelRepository.fetchLabelById(labelId).orElseThrow(() -> new LabelException("Label is not found", HttpStatus.NOT_FOUND));
		notes.getLabel().add(label);
		notesrepository.save(notes);
		labelRepository.save(label);
		return notes;
	}
	
	@Transactional
	@Override
	public Notes removingLabelFromNote(long labelId, String token, long noteId) throws NotesException, LabelException
	{
		Notes notes = notesrepository.findById(noteId).orElseThrow(() -> new NotesException("Note is not found", HttpStatus.NOT_FOUND));
		Label label = labelRepository.fetchLabelById(labelId).orElseThrow(() -> new LabelException("Label is not found", HttpStatus.NOT_FOUND));
		notes.getLabel().add(label);
		notesrepository.save(notes);
		labelRepository.save(label);
		return notes;
	}

}
