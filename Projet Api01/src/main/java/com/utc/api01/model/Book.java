package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "BOOK" )
public class Book {
	
	@Id
	@GenericGenerator(name="IDBOOK", strategy = "INCREMENT")
	private int idBook;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "autor")
	private String autor;
	
	@Column(name = "type")
	private String type;

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
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param idBook
	 * @param title
	 * @param autor
	 * @param type
	 */
	public Book(int idBook, String title, String autor, String type) {
		super();
		this.idBook = idBook;
		this.title = title;
		this.autor = autor;
		this.type = type;
	}

	/**
	 * 
	 */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
