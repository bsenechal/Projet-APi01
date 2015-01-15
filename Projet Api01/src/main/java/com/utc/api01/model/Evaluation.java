package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "EVALUATION" )
public class Evaluation {
	
	@Id
	@GenericGenerator(name="IDEVALUATION", strategy = "INCREMENT")
	private int idEvaluation;
	
	@Column(name="status")
	private int status;
	
	@ManyToOne
	@JoinColumn(name="FKBOOK")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="FKUSER")
	private User user;
}
