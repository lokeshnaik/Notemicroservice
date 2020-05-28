package com.bridgelabz.notemicroservice.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bridgelabz.notemicroservice.entity.Label;
@Repository
public class LabelRepositoryImplementation implements LabelRepository
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Label save(Label information)
	{
		Session session = entityManager.unwrap(Session.class);
		session.save(information);
		return information;
	}
	
	@Override
	public Optional<Label> fetchLabelById(Long id) {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Label where label_id=:id").setParameter("id", id).uniqueResultOptional();
		
	}
	@Override
	public int deleteLabelpermanently(Long labelId) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("DELETE FROM Label where label_id=:id");
		query.setParameter("id", labelId);
		int result = query.executeUpdate();
		return result;
	}
	
	@Override
	public List<Label> getAllLabel(Long id) {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("FROM Label WHERE user_Id='" + id + "'").getResultList();
	}

	
}
