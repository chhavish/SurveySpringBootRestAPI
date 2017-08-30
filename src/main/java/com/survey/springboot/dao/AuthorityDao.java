package com.survey.springboot.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.survey.springboot.model.Authority;
import com.survey.springboot.model.Users;

@Transactional
@Repository
public class AuthorityDao implements Iauthoritydao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Authority findById(long id) {
		return entityManager.find(Authority.class, id);
	}

}
