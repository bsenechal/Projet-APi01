package com.utc.api01.service;

import java.util.List;

public interface GeneriqueService <MaClasse>{
	
	public void add(MaClasse u);
	
	public void update(MaClasse u);
	
	public List<MaClasse> list();
	
	public MaClasse getById(int id);
	
	public void remove(int id);

}
