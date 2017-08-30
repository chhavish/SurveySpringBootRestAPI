package com.survey.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.survey.springboot.model.Users;


@Transactional
@Repository
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Users findById(long id) {
		return entityManager.find(Users.class, id);
	}

	@Override
	public Users findByName(String name) {
		return entityManager.find(Users.class, name);
	}

	@Override
	public void saveUser(Users user) {
		entityManager.persist(user);
	}

	@Override
	public void updateUser(Users user) {
		Users user1 = findById(user.getId());
		user1.setName(user.getName());
		user1.setAge(user.getAge());
		user1.setSalary(user.getSalary());
		entityManager.flush();

	}

	@Override
	public void deleteUserById(long id) {
		entityManager.remove(findById(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Users> findAllUsers() {
		String hql = "FROM User as usr ORDER BY usr.id";
		return (List<Users>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public void deleteAllUsers() {

		// String hql = "DELETE from User";
		// SessionFactory factory =
		//
		// Query query = session.createQuery(hql);
	}

	@Override
	public boolean isUserExist(Users user) {
		String hql = "FROM Users as usr WHERE usr.name = ?  or usr.username = ?";
		int count = entityManager.createQuery(hql).setParameter(1, user.getName()).setParameter(2, user.getUsername()).getResultList().size();
		return  count > 0 ? true : false;
	}

	@Override
	public void registerUser(Users user) {
		
		entityManager.persist(user);
		
	}

}
