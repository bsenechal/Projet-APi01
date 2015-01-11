package com.utc.api01.service;

import java.util.List;

import com.utc.api01.dao.GeneriqueDao;

public class GeneriqueServiceImpl<MaClasse> implements GeneriqueService<MaClasse> {

	private GeneriqueDao<MaClasse> dao;
	
	public GeneriqueServiceImpl(Class<MaClasse> tClass) {
		super();
	}
	
	public GeneriqueServiceImpl() {
		super();
	}
	
	public void setDao(GeneriqueDao<MaClasse> dao) {
		this.dao = dao;
	}
	
	public GeneriqueDao<MaClasse> getDao() {
		return dao;
	}
	
	@Override
	public void add(MaClasse u) {
		this.dao.add(u);	
	}

	@Override
	public void update(MaClasse u) {
		this.dao.update(u);	
	}

	@Override
	public List<MaClasse> list() {
		return this.dao.list();
	}

	@Override
	public MaClasse getById(int id) {
		return this.dao.getById(id);
	}

	@Override
	public void remove(int id) {
		this.dao.remove(id);
	}
}
