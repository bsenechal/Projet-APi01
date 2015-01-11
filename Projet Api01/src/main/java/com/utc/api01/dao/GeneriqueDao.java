package com.utc.api01.dao;

import java.util.List;

public interface GeneriqueDao <MaClasse> {

	public void add(MaClasse c);
	
	public void update(MaClasse c);
	
	public List<MaClasse> list();
	
	public MaClasse getById(int id);
	
	public void remove(int id);
	
}
