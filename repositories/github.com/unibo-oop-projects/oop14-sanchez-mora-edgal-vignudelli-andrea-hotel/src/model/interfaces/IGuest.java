package model.interfaces;

import java.time.LocalDate;

public interface IGuest {

	/**
	 * Metodo che ritorna il nome del guest.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Metodo che ritorna il cognome del guest.
	 * 
	 * @return
	 */

	public String getSurname();

	/**
	 * 
	 * Metodo che ritorna il codice fiscale del guest.
	 * 
	 * @return
	 */

	public String getCF();

	/**
	 * Metodo che ritorna la rappresentazione in LocalDate della data di nascita
	 * del guest.
	 * 
	 * @return
	 */

	public LocalDate getBirthDate();

	/**
	 * 
	 * Metodo che ritorna l'età del guest, usato per il check su childage e
	 * babyage.
	 * 
	 * @return
	 */

	public int getAge();

}
