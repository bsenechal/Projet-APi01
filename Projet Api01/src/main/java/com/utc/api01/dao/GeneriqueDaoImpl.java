package com.utc.api01.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GeneriqueDaoImpl<MaClasse> implements GeneriqueDao<MaClasse>{

	private SessionFactory sessionFactory;
	private final Class<MaClasse> tClass;
	
	
	public GeneriqueDaoImpl(Class<MaClasse> tClass) {
		super();
		this.tClass = tClass;
	}

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public void add(MaClasse c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);	
	}

	@Override
	public void update(MaClasse c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MaClasse> list() {
		Session session = this.sessionFactory.getCurrentSession();
		List<MaClasse> list = session.createQuery("from User").list();
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MaClasse getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		MaClasse c = (MaClasse) session.load(this.tClass, new Integer(id));
		return c;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void remove(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		MaClasse c = (MaClasse) session.load(this.tClass, id);
		if(null != c){
			session.delete(c);
		}
	}

}
