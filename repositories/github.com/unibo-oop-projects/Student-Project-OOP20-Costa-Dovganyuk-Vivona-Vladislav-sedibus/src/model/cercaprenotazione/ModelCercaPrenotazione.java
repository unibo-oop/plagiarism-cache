package model.cercaprenotazione;

import java.time.LocalDate;

import model.piantina.Prenotazione;
import model.utili.Periodo;

public interface ModelCercaPrenotazione {

	/**
	 * @param cod is the entered code
	 * @param cognome is the surname entered
	 * @param periodo is the chosen period
	 */
	void prendiDati(String cod, String cognome, Periodo periodo);
	
	/**
	 * looks for data in the database
	 * @return true if founds in the db, false otherwise
	 */
	boolean cercaDati();
	
	/**
	 * @return the reservation made by the customer
	 */
	Prenotazione getPrenotazione();
	
	/**
	 * @return the name taken from the customer's reservation
	 */
	String getNome();
	
	/**
	 * @return the email taken from the customer's booking
	 */
	String getEmail();
	
	/**
	 * @return the phone taken from the customer's reservation
	 */
	String getTelefono();
	
	/**
	 * @return the date of the reservation made by the customer
	 */
	LocalDate getData();
	
	/**
	 * @return the number of seats reserved by the customer
	 */
	String getPosti();
	
	/**
	 * @return the id/name of the table occupied by the customer
	 */
	String getIdTavolo();

	/**
	 * deletes the reservation 
	 */
	boolean eliminaPrenotazione();
	
}
