package model.interfaces;

import java.time.LocalDate;
import java.util.List;

import model.Booking;
import model.Customer;
import model.RoomType;

public interface IRoom {

	/**
	 * Metodo che ritorna il numero di stanza associato a questa stanza.
	 * 
	 * @return roomNumber
	 */
	public Integer getNumber();

	/**
	 * Metodo che ritorna il numero massimo di ospiti che questa stanza può
	 * ospitare.
	 * 
	 * @return
	 */

	public Integer getMaxGuestsNumber();

	/**
	 * Metodo che ritorna il tipo di stanza(suite, premium...)
	 * 
	 * @return
	 */

	public RoomType getType();

	/**
	 * Metodo che ritorna la lista dei customer che hanno effettuato dei booking
	 * in questa stanza. Nelle varie ricerche si è rivelato più conveniente
	 * avere una traccia di tutti i customers relativi ad una stanza, e la
	 * ricerca booking per booking all'interno dell'istanza di hotel risulta più
	 * onerosa
	 * 
	 * @return
	 */

	public List<Customer> getCustomerList();

	/**
	 * Metodo per l'aggiunta di un customer, viene richiamato in fase di
	 * creazione booking.
	 * 
	 * @param customer
	 */

	public void addCustomer(final Customer customer);

	/**
	 * Metodo per la rimozione di un customer dalla lista dei customers, viene
	 * richiamato in fase di cancellazione booking.
	 * 
	 * @param customer
	 */

	public void deleteCustomer(final Customer customer);

	/**
	 * Metodo che ritorna il customer che attualmente occupa la stanza, nel caso
	 * la stanza sia libera ritorna un null.
	 * 
	 * @return
	 */

	public Customer getActualCustomer();

	/**
	 * Metodo usato per settare l'actual customer, usato in fase di avvio del
	 * programma quando c'è da computare l'actualCustomer di LocalDate.now().
	 * 
	 * @param customer
	 */

	public void setActualCustomer(final Customer customer);

	/**
	 * Metodo che ritorna un booleano e fa il check sull'actualcustomer, se ==
	 * null return false, altrimenti return true
	 * 
	 * @return
	 */

	public boolean isBusy();

	/**
	 * Metodo che presa una data in ingresso fa il check per ogni customer di
	 * quella room se c'è qualcuno che ha un booking in quel determinato giorno.
	 * Usata nella roomView per "andare avanti nel tempo" simulando la
	 * situazione dell'hotel in una determinata data.
	 * 
	 * @param date
	 *            la data sulla quale fare il check
	 * @return il booking relativo a quella data
	 */

	public Booking getBookingDay(final LocalDate date);

	/**
	 * Metodo che presa in ingresso una data ritorna un booleano true or false
	 * in base al fatto che in quella determinata data la stanza sia occupata o
	 * meno.
	 * 
	 * @param date
	 * @return true se c'è un booking a quella data, false altrimenti.
	 */

	public boolean isBusyThisDay(final LocalDate date);

	public String toString();

}
