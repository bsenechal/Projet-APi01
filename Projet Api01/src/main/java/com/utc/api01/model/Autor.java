package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "autor" )
public class Autor {
	
	@Id
	@GenericGenerator(name="IDAUTOR", strategy = "INCREMENT")
	private int idAutor;
	
	@Column(name = "name")
	private String name;

	/**
	 * 
	 * @return the Autor's id
	 */
	public int getIdAutor() {
		return idAutor;
	}

	/**
	 * 
	 * @param idAutor to set
	 */
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	/**
	 * 
	 * @return the Autor's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param idAutor
	 * @param name
	 */
	public Autor(int idAutor, String name) {
		super();
		this.idAutor = idAutor;
		this.name = name;
	}
	
}
