package com.bridgelabz.notemicroservice.repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bridgelabz.notemicroservice.entity.Notes;
@Repository
public class NotesRepositoryImplementation implements NotesRepository 
{
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public Notes save(Notes notesInformation) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(notesInformation);
		return notesInformation;
	}

	@Override
	public boolean deleteNotes(long notesId, long userId) {
		Session session = entityManager.unwrap(Session.class);
		System.out.println(notesId);
		Query query = session.createQuery("DELETE FROM Notes where note_id=:id").setParameter("id", notesId);		
		int result = query.executeUpdate();
		if (result >= 1) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public List<Notes> getPinnedNotes(long userid) 
	{
		Session session = entityManager.unwrap(Session.class);
		List list = session.createQuery("From Notes where user_Id='" + userid + "'" + "and is_Pinned=true").getResultList();
		return list;
	}

	@Override
	public List<Notes> getArchievedNotes(long userid) 
	{
		Session session = entityManager.unwrap(Session.class);
		List list = session.createQuery("From Notes where user_Id='" + userid + "'" + "and is_Archieved=true").getResultList();
		return list;
	}

	@Override
	public List<Notes> getTrashedNotes(long userid) 
	{
		Session session = entityManager.unwrap(Session.class);
		List list = session.createQuery("from Notes where user_Id='" + userid + "'" + "and is_Trashed=true").getResultList();
		return list;
	}
 
	public boolean updateNotes( Notes information,long id)
	{
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("update Notes set title=:title, description=:description where user_id=:id").setParameter("title", information.getTitle())
				.setParameter("description", information.getDescription()).setParameter("id", id);
		int result = query.executeUpdate();
		if (result>=1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public List<Notes> getAllNotes(long userid) {
		Session session = entityManager.unwrap(Session.class);		
		List list = session.createQuery("from Notes where user_Id=:userid").setParameter("userid", userid).getResultList();
		return list;	
	}

	@Override
	public List<Notes> getAllTrashedNotes(long userid)
	{
		Session session = entityManager.unwrap(Session.class);
		List list = session.createQuery("from Notes where user_Id='" + userid + "'" + "and is_Trashed=true").getResultList();
		return list;
	}
	@Override
	public List<Notes> getAllArchievedNote(long userid)
	{
		Session session = entityManager.unwrap(Session.class);
		List list = session.createQuery("from Notes where user_Id='" + userid + "'" + "and is_Archieved=true").getResultList();
		return list;
	}
	@Override
	public List<Notes> getallpinnednotes(long userid)
	{
		Session session = entityManager.unwrap(Session.class);
		List list = session.createQuery("from Notes where user_Id='" + userid + "'" + "and is_Pinned=true").getResultList();
		return list;
	}
	@Override
	public Optional<Notes> findById(long id)
	{
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Notes where id=:id").setParameter("id", id).uniqueResultOptional();

	}

	@Override
	public Optional<Notes> getNoteById(Long noteid, Long userid) 
	{
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Notes where note_Id='"+noteid+"'"+"and user_Id='"+userid).setParameter("noteid", noteid).setParameter("userid", userid).uniqueResultOptional();
	}
	

}
