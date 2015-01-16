package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "QUESTIONS")
public class Question {

    @Id
    @GenericGenerator(name = "IDQUESTIONS", strategy = "INCREMENT")
    @Column(name = "IDQUESTIONS")
    private int idQuestion;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "valMax")
    private Integer valMax;

    @Column(name = "ponderation")
    private int ponderation;
    
    

    /**
     * @param idQuestion
     * @param libelle
     * @param valMax
     * @param ponderation
     */
    public Question(int idQuestion, String libelle, Integer valMax,
            Integer ponderation) {
        super();
        this.idQuestion = idQuestion;
        this.libelle = libelle;
        this.valMax = valMax;
        this.ponderation = ponderation;
    }

    /**
     * 
     */
    public Question() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return the idQuestion
     */
    public int getIdQuestion() {
        return idQuestion;
    }

    /**
     * @param idQuestion
     *            the idQuestion to set
     */
    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    /**
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle
     *            the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * @return the valMax
     */
    public Integer getValMax() {
        return valMax;
    }

    /**
     * @param valMax
     *            the valMax to set
     */
    public void setValMax(Integer valMax) {
        this.valMax = valMax;
    }

    /**
     * @return the ponderation
     */
    public Integer getPonderation() {
        return ponderation;
    }

    /**
     * @param ponderation
     *            the ponderation to set
     */
    public void setPonderation(Integer ponderation) {
        this.ponderation = ponderation;
    }
}
