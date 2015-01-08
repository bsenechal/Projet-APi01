package com.utc.api01.dao;

import java.util.List;

import com.utc.api01.model.User;

public interface UserDao {
	
	/**
	 * Ajoute un utilisateur
	 * @param u
	 */
	public void addUser(User u);
	
	/**
	 * Met à jour un utilisateur
	 * @param u
	 */
	public void updateUser(User u);

	/**
	 * Renvoi une liste de tous les utilisateurs
	 * @return List<User>
	 */
	public List<User> listUsers();
	
	/**
	 * Renvoi l'utilisateur dont l'identifiant est passé en paramètre
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
	
	/**
	 * Supprime un utilisateur
	 * @param id
	 */
	public void removeUser(int id);

}