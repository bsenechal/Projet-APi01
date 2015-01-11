package com.utc.api01.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utc.api01.dao.GeneriqueDao;
import com.utc.api01.model.User;

@Service
public class UserServiceImpl implements GeneriqueService<User>{
	
	private GeneriqueDao<User> userDao;

	public void setUserDao(GeneriqueDao<User> userDao) {
		this.userDao = userDao;
	}

	@Transactional
	public void addUser(User u) {
		this.userDao.add(u);
	}

	@Transactional
	public void updateUser(User u) {
		this.userDao.update(u);
	}

	@Transactional
	public List<User> listUsers() {
		return this.userDao.list();
	}

	@Transactional
	public User getUserById(int id) {
		return this.userDao.getById(id);
	}

	@Transactional
	public void removeUser(int id) {
		this.userDao.remove(id);
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
