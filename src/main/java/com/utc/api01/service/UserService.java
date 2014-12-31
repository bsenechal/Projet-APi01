package com.utc.api01.service;

import java.util.List;

import com.utc.api01.model.User;


public interface UserService {
	
	/**
	 * Ajout un utilisateur
	 * @param u
	 */
	public void addUser(User u);
	
	/**
	 * Met à jour un utilisateur
	 * @param u
	 */
	public void updateUser(User u);
	
	/**
	 * Retourne la liste de tous les utilisateurs
	 * @return
	 */
	public List<User> listUsers();
	
	/**
	 * Retourne l'utilisateur dont l'identifiant est égal à celui passé en paramètre
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
	
	/**
	 * Supprime un utilisateur
	 * @param id
	 */
	public void removeUser(String username);
	
	/**
	 * Recherche un utilisateur par login
	 * @param username
	 * @return
	 */
	public User fingByUsername(String username);
}
