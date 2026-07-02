package controller;

import java.util.Map;

import cartasoci.User;
import exceptions.MissingDataException;
import exceptions.MissingUserException;
import exceptions.UserAlreadyExisting;
import exceptions.WrongDataException;

/**
 * 
 * @author Alberto Mulazzani
 *
 */
public interface IFidelityController {
	
	/**
	 * 
	 * @param fields of the user to add
	 * @throws UserAlreadyExisting if the user is already in the map
	 * @throws MissingDataException if some data is missing
	 * @throws WrongDataException if some data is wrong
	 */
	void addPerson(final String... fields) throws
	UserAlreadyExisting, MissingDataException, WrongDataException;
	
	/**
	 * 
	 * @param id of the user to remove
	 * @throws NullPointerException if fields are null
	 * @throws IllegalArgumentException if there are illegal argument
	 * @throws MissingUserException if the user is not in the map
	 */
	void removePerson(final String id) throws NullPointerException,
	IllegalArgumentException, MissingUserException;

	/**
	 * 
	 * @param fields of the user
	 * @param price of the book sold
	 * @throws NullPointerException if fields are null
	 * @throws IllegalArgumentException if there are illegal argument
	 * @throws MissingUserException if the user is not in the map
	 */
	void addPoints(final String[] fields, final double price) throws NullPointerException, IllegalArgumentException, MissingUserException;

	/**
	 * 
	 * @param fields id to search
	 * @return the user
	 * @throws NullPointerException if fields are null
	 * @throws IllegalArgumentException if there are illegal argument
	 * @throws MissingUserException if the user is not in the map
	 */
	User searchID(final String fields) throws NullPointerException,
	IllegalArgumentException, MissingUserException;

	/**
	 * 
	 * @param name of user
	 * @param surname of user
	 * @return the user
	 * @throws NullPointerException if fields are null
	 * @throws IllegalArgumentException 
	 * @throws MissingUserException if the user is not in the map
	 */
	User searchName(final String name, final String surname) throws NullPointerException,
	IllegalArgumentException, MissingUserException;

	/**
	 * 
	 * @return the map with id and user
	 */
	Map<Integer, User> getMap();

	/**
	 * 
	 * @return the current id
	 */
	int getCurrent();

	/**
	 * 
	 * @param map to load
	 */
	void loadMemory(final Map<Integer, User> map);

	/**
	 * 
	 * @param b user to modify
	 * @param jfields the fields to modify
	 * @throws WrongDataException if some data is wrong
	 */
	void modifyUser(final User b, final String... jfields) throws WrongDataException;


}
