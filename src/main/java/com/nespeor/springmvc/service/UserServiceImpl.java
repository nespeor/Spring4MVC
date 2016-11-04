package com.nespeor.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nespeor.springmvc.model.User;
import com.nespeor.springmvc.model.UserDAO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDao;

	public List<User> findAllUsers() {
		return userDao.findAll();
	}
	
	public User findById(Integer id) {
		return userDao.findById(id);
	}
	
	public User findByName(String name) {
		
		List<User> users = userDao.findByName(name);
		for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}
	
	public void saveUser(User user) {
		user.setId(userDao.generateId());
		userDao.create(user);
	}

	public void updateUser(User user) {
		userDao.update(user);
	}

	public void deleteUserById(Integer id) {
		userDao.remove(id);
	}

	public boolean isUserExist(User user) {
		List<User> users = userDao.findByName(user.getUsername());
		return users.size()>0;
	}
	
	public void deleteAllUsers(){
		//users.clear();
	}

}
