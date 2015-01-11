package com.utc.api01.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utc.api01.dao.UserDao;
import com.utc.api01.model.User;
import com.utc.api01.service.UserService;

@Service
public class UserServiceImpl implements GeneriqueService<User>{
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional
	public void addUser(User u) {
		this.userDao.addUser(u);
	}

	@Transactional
	public void updateUser(User u) {
		this.userDao.updateUser(u);
	}

	@Transactional
	public List<User> listUsers() {
		return this.userDao.listUsers();
	}

	@Transactional
	public User getUserById(int id) {
		return this.userDao.getUserById(id);
	}

	@Transactional
	public void removeUser(int id) {
		this.userDao.removeUser(id);
	}

	@Override
	public void add(User u) {
		addUser(u);
		
	}

	@Override
	public void update(User u) {
		update(u);
		
	}

	@Override
	public List<User> list() {
		return listUsers();
	}

	@Override
	public User getById(int id) {
		return getUserById(id);
	}

	@Override
	public void remove(int id) {
		removeUser(id);		
	}
}
