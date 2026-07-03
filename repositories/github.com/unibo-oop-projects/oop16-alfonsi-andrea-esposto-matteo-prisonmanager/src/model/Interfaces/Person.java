package model.Interfaces;

import java.util.Date;
/**
 * questa interfaccia rappresenta una persona
 */
public interface Person {

	/**
	 * Metodo che ritorna il nome della persona.
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * Metodo che ritorna il cognome della persona.
	 * 
	 * @return the surname
	 */

	public String getSurname();

	/**
	 * Metodo che ritorna la data di nascita dellla persona.
	 * 
	 * @return the birth date
	 */

	public Date getBirthDate();
	/**
	 * Metodo che setta il nome della persona.
	 * 
	 * @return
	 */
	public void setName(String name);

	/**
	 * Metodo che setta il cognome della persona.
	 * 
	 * @return
	 */

	public void setSurname(String surname);

	/**
	 * Metodo che setta la data di nascita dellla persona.
	 * 
	 * @return
	 */

	public void setBirthDate(Date birth);
}
