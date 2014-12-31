package com.utc.api01.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utc.api01.model.User;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
	}

	@Override
	public void updateUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> usersList = session.createQuery("from User").list();
		return usersList;
	}

	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User p = (User) session.load(User.class, new Integer(id));
		return p;
	}

	@Override
	public void removeUser(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		User p = (User) session.load(User.class, new String(username));
		if(null != p){
			session.delete(p);
		}
	}
	
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = getSessionFactory().getCurrentSession().createQuery("from User where username=?")
				.setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}