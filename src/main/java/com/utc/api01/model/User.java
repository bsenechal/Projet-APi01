package com.utc.api01.model;

import java.util.HashSet;
import java.util.Set;

public class User{

	private String username;
	private String password;
	private boolean enabled;
	private String firstname;
	private String lastname;
	private String email;
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	/**
	 * 
	 */
	public User() {
		super();
	}
	
	/**
	 * @param username
	 * @param password
	 * @param enabled
	 */
	public User(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	/**
	 * @param username
	 * @param password
	 * @param enabled
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param userRole
	 */
	public User(String username, String password, boolean enabled,
			String firstname, String lastname, String email,
			Set<UserRole> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.userRole = userRole;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
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
	 * @param enabled the enabled to set
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
	 * @param firstname the firstname to set
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
	 * @param lastname the lastname to set
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
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the userRole
	 */
	public Set<UserRole> getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}


}
