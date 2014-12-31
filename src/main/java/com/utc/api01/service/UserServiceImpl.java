package com.utc.api01.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.utc.api01.dao.UserDao;
import com.utc.api01.model.User;
import com.utc.api01.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public void addUser(User u) {
		this.userDao.addUser(u);
	}

	@Override
	@Transactional
	public void updateUser(User u) {
		this.userDao.updateUser(u);
	}

	@Override
	@Transactional
	public List<User> listUsers() {
		return this.userDao.listUsers();
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		return this.userDao.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(String username) {
		this.userDao.removeUser(username);
	}
	
	@Override
	@Transactional
	public User fingByUsername(String username){
		return this.userDao.findByUserName(username);
	}

}
