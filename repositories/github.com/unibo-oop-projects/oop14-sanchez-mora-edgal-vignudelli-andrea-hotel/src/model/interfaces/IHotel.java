package model.interfaces;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import model.Booking;
import model.Catalog;
import model.Extra;
import model.Guest;
import model.Pair;
import model.Room;

public interface IHotel {

	/**
	 * Metodo che ritorna il catalogo associato a quest'hotel.
	 * 
	 * @return Catalog
	 */
	public Catalog getCatalog();

	/**
	 * 
	 * Metodo usato per settare il catalogo con quello passato in ingresso
	 * 
	 * @param catalog
	 *            il catalogo che verrà inizializzato.
	 */

	public void setCatalog(final Catalog catalog);

	/**
	 * Metodo usato per ritornare la lista di tutti i customers che hanno un
	 * booking presente nel nostro hotel.
	 * 
	 * @return
	 */
	public HashSet<Guest> getCustomerList();

	/**
	 * Metodo usato per ritornare la lista di tutte le stanze presenti in questo
	 * hotel.
	 * 
	 * @return
	 */

	public List<Room> getRoomList();

	/**
	 * Metodo usato per ritornare la lista di tutti i booking presenti in questo
	 * hotel.
	 * 
	 * @return
	 */

	public List<Booking> getBookingList();

	/**
	 * Metodo usato per ritornare la lista di tutti gli extra dei quali si può
	 * usufruire in questo hotel.
	 * 
	 * @return
	 */

	public List<Extra> getExtraList();

	/**
	 * Metodo usato per aggiungere un nuovo guest, in fase di creazione del
	 * booking nel caso del customer, o durante il checkin nel caso dei guests.
	 * 
	 * @param guest
	 */

	public void addGuest(final Guest guest);

	/**
	 * Metodo usato per rimuovere un guest, passato in ingresso, dalla guestlist
	 * associata a questo hotel(inserimento errato o cancellazione booking)
	 * 
	 * @param guest
	 */

	public void removeGuest(final Guest guest);

	/**
	 * Metodo che aggiunge una nuova stanza all'hotel, prende in ingresso la
	 * stanza.
	 * 
	 * @param room
	 */

	public void addRoom(final Room room);

	/**
	 * Metodo che rimuove una stanza dall'hotel.
	 * 
	 * @param room
	 */

	public void removeRoom(final Room room);

	/**
	 * Metodo che aggiunge un booking all'hotel.
	 * 
	 * @param booking
	 */

	public void addBooking(final Booking booking);

	/**
	 * Metodo per la rimozione di un booking, prende in ingresso il booking da
	 * rimuovere
	 * 
	 * @param booking
	 */

	public void removeBooking(final Booking booking);

	/**
	 * Metodo per la rimozione di un extra, prende in ingresso l'extra da
	 * rimuovere
	 * 
	 * @param extra
	 */

	public void removeExtra(final Extra extra);

	/**
	 * NON FUNZIONA
	 */

	public void addBalance(final LocalDate date, final String voiceOfChange, final double amount,
			final boolean isIncoming);

	/**
	 * NON FUNZIONA
	 */

	public void removeBalance(final String date, final int index, final boolean isIncoming);

	/**
	 * Metodo usato per aggiungere una voce tra gli extra dei quali si può
	 * usufruire questo hotel
	 * 
	 * @param extra
	 *            il nuovo extra da aggiungere ala lista dei disponibili
	 */

	public void addExtra(final Extra extra);

	/**
	 * NON FUNZIONA
	 */

	public HashMap<LocalDate, List<Pair<String, Double>>> getIncoming();

	/**
	 * NON FUNZIONA
	 */

	public HashMap<LocalDate, List<Pair<String, Double>>> getOutcoming();

	/**
	 * NON FUNZIONA
	 */

	public double getActualBalance();

	/**
	 * Metodo che presi in ingresso due stringhe che rappresentano nome e
	 * cognome di un customer tornano in uscita una lista di booking associata
	 * ai customer con nome e cognome corrispondenti a quelli presei in
	 * ingresso. (Metodo pensato per modellare una situazione nella quale un
	 * cliente che ha effettuato una prenotazione debba apportare modifiche o
	 * cancellare il booking)
	 * 
	 * @param name nome del customer
	 * @param surname cognome del customer
	 * @return
	 */

	public List<Booking> findCustomer(final String name, final String surname);

	/**
	 * NON FUNZIONA
	 * @param date
	 * @return
	 */

	public String dailyBalanceVoices(final LocalDate date);
	
	/**
	 * NON FUNZIONA
	 * @param start
	 * @param end
	 * @return
	 */
	public String intervalBalanceVoices(final LocalDate start, final LocalDate end);
	
	/**
	 * NON FUNZIONA
	 * @return
	 */

	public String totalBalanceVoices();
}
