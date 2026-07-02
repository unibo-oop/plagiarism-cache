package model.piantina;

import java.time.LocalDate;
import java.util.List;

import model.utili.Cliente;
import model.utili.Periodo;

public interface MainTableModel {

	/**
	 * @param date
	 * @param p
	 * @return the list of IDs of the reserved tables
	 */
	List<Integer> tavoliPrenotati(LocalDate date, Periodo p);
	
	/**
	 * @param ID
	 * @return max number of seats
	 */
	int getPostiMax(int ID);
	
	/**
	 * @param idTavolo
	 * @return the reservation code relate to that table
	 */
	String getCodicePrenotazione(int idTavolo);
	
	/**
	 * @param idTavolo
	 * @return surname and name of the customer as a single String
	 */
	String getCognomeNomeCliente(int idTavolo);
	
	/**
	 * @param idTavolo
	 * @return the number of seats reserved for that table
	 */
	String getPostiPrenotati(int idTavolo);
	
	/**
	 * @param idTavolo
	 * @return the telephone number of the customer who booked for that table
	 */
	String getNumTelefonoCliente(int idTavolo);
	
	/**
	 * @param idTavolo
	 * @return the email of the customer who booked for that table
	 */
	String getEmailCliente(int idTavolo);
	
	/**
	 * @param idTavolo
	 * @return the customer of the id's table
	 */
	Cliente getCliente(int idTavolo);
	
	/**
	 * @param codicePrenotazione
	 * @return the complete reservation with the code passed in input
	 */
	Prenotazione getPrenotazione(String codicePrenotazione);
	
	/**
	 * @param codicePrenotazione
	 * @return the id of the table taken from the reservation with the code passed in input
	 */
	int getIdTavolo(String codicePrenotazione);
}
