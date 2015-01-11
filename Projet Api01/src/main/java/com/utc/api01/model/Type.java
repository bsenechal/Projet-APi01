package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "type" )
public class Type {
	@Id
	@GenericGenerator(name="IDTYPE", strategy = "INCREMENT")
	private int idType;
	
	@Column(name = "name")
	private String name;

	/**
	 * 
	 * @return the type's id
	 */
	public int getIdType() {
		return idType;
	}

	/**
	 * 
	 * @param idType to set
	 */
	public void setIdType(int idType) {
		this.idType = idType;
	}

	/**
	 * 
	 * @return the type's name
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
	 * @param idType
	 * @param name
	 */
	public Type(int idType, String name) {
		super();
		this.idType = idType;
		this.name = name;
	}
	
}
