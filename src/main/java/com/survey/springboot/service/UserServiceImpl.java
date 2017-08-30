package com.survey.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.springboot.dao.IUserDAO;
import com.survey.springboot.dao.Iauthoritydao;
import com.survey.springboot.model.Authority;
import com.survey.springboot.model.Users;



@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Users> users;
	
//	static{
//		users= populateDummyUsers();
//	}

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private Iauthoritydao iauthoritydao;
	
	public List<Users> findAllUsers() {
		//return users;
		return userDAO.findAllUsers();
	}
	
	public Users findById(long id) {
//		for(User user : users){
//			if(user.getId() == id){
//				return user;
//			}
//		}
//		return null;
		
		return userDAO.findById(id);
	}
	
	public Users findByName(String name) {
//		for(User user : users){
//			if(user.getName().equalsIgnoreCase(name)){
//				return user;
//			}
//		}
//		return null;
		return userDAO.findByName(name);
	}
	
	protected Authority getAuthority(long Id){
		return iauthoritydao.findById(Id);
	}
	
	public void saveUser(Users user) {
//		user.setId(counter.incrementAndGet());
//		users.add(user);
		//user.setAuthority(getAuthority(user.getAuthority().getAuthorityId()));
		userDAO.saveUser(user);
	}

	public void updateUser(Users user) {
//		int index = users.indexOf(user);
//		users.set(index, user);
		//user.setAuthority(getAuthority(user.getAuthority().getAuthorityId()));
		userDAO.updateUser(user);
	}

	public void deleteUserById(long id) {
		
//		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
//		    User user = iterator.next();
//		    if (user.getId() == id) {
//		        iterator.remove();
//		    }
//		}
		
		userDAO.deleteUserById(id);
	}

	public boolean isUserExist(Users user) {
//		return findByName(user.getName())!=null;
		return userDAO.isUserExist(user);
	}
	
	public void deleteAllUsers(){
//		users.clear();
		userDAO.deleteAllUsers();
	}

//	private static List<User> populateDummyUsers(){
//		List<User> users = new ArrayList<User>();
//		users.add(new User(counter.incrementAndGet(),"Sam",30, 70000));
//		users.add(new User(counter.incrementAndGet(),"Tom",40, 50000));
//		users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000));
//		users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000));
//		return users;
//	}

}
