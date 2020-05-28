package com.bridgelabz.notemicroservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.notemicroservice.dto.LabelUpdate;
import com.bridgelabz.notemicroservice.dto.Labeldto;
import com.bridgelabz.notemicroservice.entity.Label;
import com.bridgelabz.notemicroservice.entity.Notes;
import com.bridgelabz.notemicroservice.exception.LabelException;
import com.bridgelabz.notemicroservice.exception.NotesException;
import com.bridgelabz.notemicroservice.response.Response;
import com.bridgelabz.notemicroservice.service.LabelServices;
@RestController
public class LabelController
{
	@Autowired
	LabelServices services;
	
	
	@PostMapping("/label/add")
	public ResponseEntity<Response> createLabel(@RequestBody Labeldto labeldto, @RequestHeader("token") String token) 
	{
		services.addLabel(labeldto, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Label  is Added ", 200, labeldto));

	}
	@PutMapping("/label/update")
	public ResponseEntity<Response> updateLabel(@RequestBody LabelUpdate label, @RequestHeader("token") String token) throws LabelException 
	{
		services.editLabel(label, token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Label is  updated ", 200, label));
    }
	@DeleteMapping("/label/delete")
	public ResponseEntity<Response> deleteLabel(@RequestBody LabelUpdate label, @RequestHeader("token") String token) throws  NotesException, LabelException {
		services.deleteLabel(label, token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Label is deleted ", 200, label));
	}
	@GetMapping("/label/getalllabels")
	public ResponseEntity<Response> getLabels(@RequestHeader("token") String token) {
		List<Label> labels = services.getLabel(token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Getting all labels  ", 200, labels));
	}
	@PostMapping("/label/maplabeltonote")
	public ResponseEntity<Response> maplabeltonote(@RequestBody Labeldto Labeldto, @RequestHeader("token") String token,@RequestParam("noteId") long noteId) throws LabelException, NotesException
	{
		Notes notes=services.maplabeltonote(Labeldto, token, noteId);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Label is created ", 200, notes));
	}
	
	@PostMapping("/label/addlabeltonote")
	public ResponseEntity<Response> addlabel(@RequestParam("labelId") Long labelId,@RequestHeader("token") String token, @RequestParam("noteId") Long noteId) throws NotesException, LabelException
	{
		Notes notes=services.addingLabelToNote(labelId, token, noteId);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("label added ", 200,notes));
	}
	@PostMapping("/label/removelabelfromnote")
	public ResponseEntity<Response> removelabelfromnote(@RequestParam("labelId") long labelId,
			@RequestHeader("token") String token, @RequestParam("noteId") long noteId) throws NotesException, LabelException {
		Notes notes=services.removingLabelFromNote(labelId, token, noteId);
		return ResponseEntity.status(HttpStatus.OK).body(new Response("label removed ", 200,notes));
	}
}
