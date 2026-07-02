package model.creaprenotazione;

import java.time.LocalDate;
import model.utili.Periodo;

public interface ModelPrenotazione {

	/**
	 * The table chosen by the user is obtained from the name/id
	 * @param idTavolo
	 */
	void prendiTavolo(String idTavolo);
	
	/**
	 * Necessary to instantiate PilotaPosto (when modifying a reservation)
	 * @param posti the seats the client wanted to occupy
	 */
	void settaPostiModifica(int posti);
	
	/**
	 * Necessary to instantiate PilotaPosto (when creating a reservation)
	 */
	void settaPostiCreazione();
	
	/**
	 * It takes the parameters necessary to identify the old reservation
	 * @param codice the code of the old reservation
	 * @param periodo the period of the old reservation
	 * @param data the date of the old reservation
	 */
	void prendiVecchiaPrenotazione(String codice, Periodo periodo, LocalDate data);
	
	/**
	 * Increase the number of seats the customer wants to occupy by 1
	 */
	void incrementaPosti();
	
	/**
	 * Decreases the number of seats the customer wants to occupy by 1
	 */
	void decrementaPosti();
	
	/**
	 * Bring the seats that the customer can occupy to minimum
	 */
	void inizializzaPosti();
	
	/**
	 * @return the number of seats the customer wants to occupy
	 */
	int postiCorrenti();
	
	/**
	 * @param nome the name user entered
	 * @param cognome the surname the user entered
	 * @param email that the user entered
	 * @param telefono the phone number the user entered
	 * @return true if the input complies with the controls, false otherwise
	 */
	boolean prendiDatiCliente(String nome, String cognome, String email, String telefono);
	
	/**
	 * Extract the period from the input string
	 * @param periodo
	 */
	void prendiPeriodo(String periodo);
	
	/**
	 * @param dataScelta the date the customer chose
	 */
	void prendiData(LocalDate dataScelta);
	
	/**
	 * Add a new reservation
	 */
	void aggiungiPrenotazione();
	
	/**
	 * Entrust the customer with the first free table of the map with the desired characteristics
	 * @return true if a table was found, false otherwise
	 */
	boolean cercaTavolo();
	
	/**
	 * Modify an existing reservation, deleting the old one but keeping the code
	 */
	void modificaPrenotazione();
	
}
