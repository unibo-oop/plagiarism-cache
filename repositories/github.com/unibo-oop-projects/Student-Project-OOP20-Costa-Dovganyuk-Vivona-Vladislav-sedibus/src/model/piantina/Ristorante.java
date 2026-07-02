package model.piantina;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import model.utili.Periodo;

public interface Ristorante {

	/**
	 * @return the list of all the tables in the restaurant
	 */
	List<Tavolo> tavoliRistorante();
	
	/**
	 * @param date
	 * @return the list of tables booked for that date
	 */
	List<Tavolo> tavoliPrenotati(LocalDate date, Periodo p);
	
	/**
	 * Call the db manager function for adding the reservation to the DB
	 * @param prenotazione
	 */
	public void nuovaPrenotazione(PrenotazioneEstesa prenotazione);
	
	/**
	 * @param p
	 * @return the map of reservations for a certain period p
	 */
	public Map<String,List<Prenotazione>> getPrenotazioni(Periodo p);
	
	/**
	 * @param data
	 * @param p
	 * @return the list of reservations relating to the 2 "keys"
	 */
	List<Prenotazione> getListPrenotazioni(LocalDate data, Periodo p);
	
	/**
	 * @param p
	 * @param codicePrenotazione
	 * @param cognome
	 * @return true if the cancellation of the reservation identified by the 3 input parameters is successful, otherwise false
	 */
	boolean eliminaPrenotazione(Periodo p, String codicePrenotazione, String cognome);
	
}
