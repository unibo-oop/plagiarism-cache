package model;

import utils.Position;

public interface Equipment {

	/**
	 * Costo dell'attrezzatura
	 * @return costo del noleggio dell'attrezzatura
	 */
	double getCost();

	/**
	 * 
	 * @return posizione dell'attrezzatura
	 */
	Position getPosition();
	
	
	/**
	 * 
	 * @return controllo se l'attrazzatura è già occupata  
	 */
	boolean isAlreadyOccupied();
	
	
	/**
	 * Noleggia l'attrezzatura
	 */
	void rent();
	
	/**
	 * Metodo per restituire
	 */
	void giveBack();
	
}
