package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "USER" )
public class Book {
	
	@Id
	@GenericGenerator(name="IDBOOK", strategy = "INCREMENT")
	private int idBook;
	
	@Column(name = "title")
	private String title;
	
	@ManyToOne
	@JoinColumn(name="IDAUTOR", table="AUTOR", nullable = false, updatable = false, insertable = false)
	@Column(name = "idAutor")
	private int idAutor;
	
	@ManyToOne
	@JoinColumn(name="IDTYPE", table="TYPE", nullable = false, updatable = false, insertable = false)
	@Column(name = "idType")
	private int idType;

	/**
	 * @return the idBook
	 */
	public int getIdBook() {
		return idBook;
	}

	/**
	 * @param idBook the idBook to set
	 */
	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the idAutor
	 */
	public int getIdAutor() {
		return idAutor;
	}

	/**
	 * @param idAutor the idAutor to set
	 */
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	/**
	 * @return the idType
	 */
	public int getIdType() {
		return idType;
	}

	/**
	 * @param idType the idType to set
	 */
	public void setIdType(int idType) {
		this.idType = idType;
	}

	/**
	 * @param idBook
	 * @param title
	 * @param idAutor
	 * @param idType
	 */
	public Book(int idBook, String title, int idAutor, int idType) {
		super();
		this.idBook = idBook;
		this.title = title;
		this.idAutor = idAutor;
		this.idType = idType;
	}

}
