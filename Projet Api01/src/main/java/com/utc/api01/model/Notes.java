package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "NOTES" )
public class Notes {
	
	@Id
	@GenericGenerator(name="IDQUESTIONS", strategy = "INCREMENT")
	private int idNotes;
	
	@Column(name="note")
	private int note;

	@ManyToOne
	@JoinColumn(name="FKEVAL")
	private Evaluation evaluation;
	
	@ManyToOne
	@JoinColumn(name="FKQUESTION")
	private Question question;
}
