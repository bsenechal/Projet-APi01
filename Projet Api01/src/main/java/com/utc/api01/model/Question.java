package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "QUESTIONS" )
public class Question {

	@Id
	@GenericGenerator(name="IDQUESTIONS", strategy = "INCREMENT")
	private int idQuestion;
	
	@Column(name = "libelle")
	private String libelle;
	
	@Column(name = "valMax")
	private String valMax;
	
	@Column(name = "ponderation")
	private String ponderation;
}
