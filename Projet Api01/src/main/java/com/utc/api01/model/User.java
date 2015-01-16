package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "USER")
public class User {
    @Id
    @GenericGenerator(name = "IDUSER", strategy = "INCREMENT")
    private int idUser;

    @Column(name = "PASSWORD")
//    @NotNull(message="TOTO !!!!!")
//    @Size(min = 6, max = 15)
    private String password;

    @Column(name = "ENABLED")
//    @NotNull
    private boolean enabled;

    @Column(name = "FIRSTNAME")
//    @NotNull
    private String firstname;

    @Column(name = "LASTNAME")
//    @NotNull
    private String lastname;

    @Column(name = "EMAIL")
//    @NotNull
//    @Email
    private String email;

    @Column(name = "TELEPHONE")
//    @NotNull
    private Integer telephone;

    @Column(name = "CREATION_DATE")
//    @NotNull
    private String creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FKROLE")
//    @NotNull
    private Role role;

    @Transient
//    @NotNull
    private int roleUser;

    @Transient
//    @NotNull
    private String confirmation;
    
    /**
     * @param idUser
     * @param password
     * @param enabled
     * @param firstname
     * @param lastname
     * @param email
     * @param telephone
     * @param creationDate
     * @param role
     * @param roleUser
     * @param confirmation
     */
    public User(int idUser, String password, boolean enabled, String firstname,
            String lastname, String email, Integer telephone,
            String creationDate, Role role, int roleUser, String confirmation) {
        super();
        this.idUser = idUser;
        this.password = password;
        this.enabled = enabled;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.telephone = telephone;
        this.creationDate = creationDate;
        this.role = role;
        this.roleUser = roleUser;
        this.confirmation = confirmation;
    }

    
    /**
     * @param password
     * @param enabled
     * @param email
     */
    public User(String password, boolean enabled, String email) {
        super();
        this.password = password;
        this.enabled = enabled;
        this.email = email;
    }


    /**
     * 
     */
    public User() {
        super();
    }

    /**
     * @return the roleUser
     */
    public int getRoleUser() {
        return roleUser;
    }

    /**
     * @param roleUser
     *            the roleUser to set
     */
    public void setRoleUser(int roleUser) {
        this.roleUser = roleUser;
    }

    /**
     * @return the confirmation
     */
    public String getConfirmation() {
        return confirmation;
    }

    /**
     * @param confirmation
     *            the confirmation to set
     */
    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    /**
     * @return the idUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param idUser
     *            the idUser to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname
     *            the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname
     *            the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telephone
     */
    public Integer getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     *            the telephone to set
     */
    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
