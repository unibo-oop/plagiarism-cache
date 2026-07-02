package controller;

import java.util.Map;

import cartasoci.FidelityCards;
import cartasoci.IFidelityCards;
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
public final class FidelityController implements IFidelityController {
	
	private final IFidelityCards cards = new FidelityCards();
	private static final IFidelityController CONTROLLER = new FidelityController();
	
	private FidelityController() {
		
	}
	
	/**
	 * 
	 * @param fields of the user to add
	 * @throws UserAlreadyExisting if the user is already in the map
	 * @throws MissingDataException if some data is missing
	 * @throws WrongDataException if some data is wrong
	 */
	public void addPerson(final String... fields) throws
			UserAlreadyExisting, MissingDataException, WrongDataException {

		checkEmpty(fields);
		checkData(fields);
		
		final User user = new User(fields[0], fields[1], fields[2]);
		cards.addPerson(user);	
	}

	/**
	 * 
	 * @param id of the user to remove
	 * @throws NullPointerException if fields are null
	 * @throws IllegalArgumentException if there are illegal argument
	 * @throws MissingUserException if the user is not in the map
	 */
	public void removePerson(final String id) throws NullPointerException,
			IllegalArgumentException, MissingUserException {
		
		cards.removePerson(Integer.parseInt(id));
	}

	/**
	 * 
	 * @param fields of the user
	 * @param price of the book sold
	 * @throws NullPointerException if fields are null
	 * @throws IllegalArgumentException if there are illegal argument
	 * @throws MissingUserException if the user is not in the map
	 */
	public void addPoints(final String[] fields, final double price) throws NullPointerException, 
		IllegalArgumentException, MissingUserException {
		
		final Integer copies = Integer.parseInt(fields[2]);
		final int tot = (int) ((copies * price) / 10);
		
		cards.addPoints(this.searchID(fields[3]), tot);
	}
	
	/**
	 * 
	 * @param fields id to search
	 * @return the user
	 * @throws NullPointerException if fields are null
	 * @throws IllegalArgumentException if there are illegal argument
	 * @throws MissingUserException if the user is not in the map
	 */
	public User searchID(final String fields) throws NullPointerException,
			IllegalArgumentException, MissingUserException {

		return cards.searchID(Integer.parseInt(fields));
	}

	/**
	 * 
	 * @param name of user
	 * @param surname of user
	 * @return the user
	 * @throws NullPointerException if fields are null
	 * @throws IllegalArgumentException 
	 * @throws MissingUserException if the user is not in the map
	 */
	public User searchName(final String name, final String surname) throws NullPointerException,
			IllegalArgumentException, MissingUserException {
		return cards.searchName(name, surname);
	}

	/**
	 * 
	 * @return the map with id and user
	 */
	public Map<Integer, User> getMap() {
		return cards.getMap();
	}
	
	/**
	 * 
	 * @return the current id
	 */
	public int getCurrent() {
		return cards.getCurrent();
	}
	
	/**
	 * 
	 * @param map to load
	 */
	public void loadMemory(final Map<Integer, User> map) {
		cards.loadMemory(map);
	}
	
	private void checkData(final String... fields) throws WrongDataException {

		if (fields[2].length() != 0) {
			if (!fields[2].contains("@")) {
				throw new WrongDataException();
			}			
		}

		
	}
	
	private void checkEmpty(final String... fields) throws MissingDataException { 
		
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].length() == 0) {
				throw new MissingDataException();
			}
		}
		
	}
	
	/**
	 * 
	 * @param b user to modify
	 * @param jfields the fields to modify
	 * @throws WrongDataException if some data is wrong
	 */
	public void modifyUser(final User b, final String... jfields) throws WrongDataException {
		
			checkData(jfields);		
	
			cards.modifyPerson(b, jfields);
		}	
	/**
	 * 
	 * @return the controller
	 */
	public static IFidelityController getIstance() {
		return CONTROLLER;
	}
}


	

