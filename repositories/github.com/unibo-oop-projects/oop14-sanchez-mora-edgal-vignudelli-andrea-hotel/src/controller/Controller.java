package controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import controller.interfaces.IController;
import model.Booking;
import model.Customer;
import model.Extra;
import model.Hotel;
import model.Room;
import model.RoomType;

public final  class Controller implements Serializable, IController {

	private static final long serialVersionUID = -7643493705668362725L;
	private static final String error = "ERRORE";
	private static final String nullString = "COMPLETARE TUTTI I CAMPI";
	private static final String isNotNumber = "UN CAMPO NUMERICO CONTIENE CARATTERI NON VALIDI";
	private static final String stringContainsNumber = "UNA STRINGA CONTIENE VALORI NUMERICI NON VALIDI";
	private static Controller myInstance;

	/**
	 * Questa classe funge da utility fornendo metodi per il check
	 * dell'inserimento corretto di parametri nelle varie label/textfield oltre
	 * che fungere da punto di raccordo tra view e model per alcuni inserimenti
	 * delicati come booking ad esempio.
	 * Implementa il pattern Singleton.
	 */

	private Controller() {

	}

	/**
	 * Metodo che controlla se la stanza associata al customer passato in
	 * ingresso � occupata in una determinata data.
	 * 
	 * @param date
	 *            la data per la quale controllare se la stanza � occupata
	 * @param customer
	 *            il customer associato a quella determinata stanza
	 * @return true if room is busy, false if room is free
	 */

	public boolean checkIfRoomIsBusy(final LocalDate date, final Customer customer) {
		if ((date.isEqual(customer.getBooking().getStartDate()) || date.isEqual(customer.getBooking().getEndDate()))
				|| (date.isAfter(customer.getBooking().getStartDate())
						&& date.isBefore(customer.getBooking().getEndDate()))) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo che controlla se la stanza associata ad un customer passato in
	 * ingresso � occupata per un determinato intervallo di tempo.
	 * 
	 * @param start
	 *            la data di inizio per il checking sulla room
	 * @param end
	 *            la data di fine per il checking sulla room
	 * @param customer
	 *            il customer associato alla room da controllare
	 * @return true if room is busy for even one day of the period, false
	 *         altrimenti
	 */

	public boolean checkIfRoomIsBusyPeriod(final LocalDate start, final LocalDate end, final Customer customer) {
		for (int i = 0; i < Period.between(start, end).getDays(); i++) {
			if (checkIfRoomIsBusy(start.plusDays(i), customer)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo che ritorna lo stato attuale di tutte le stanze in una determinata
	 * data.
	 * 
	 * @param date
	 *            la data sulla quale eseguire il chec.k
	 * @param rooms
	 *            la lista di stanze dell'hotel
	 * @return myMap la mappa con le stanze dell'hotel mappate con valori
	 *         boolean ad indicare se la room � busy o meno in quel determinato
	 *         giorno.
	 */

	public Map<Room, Boolean> roomsStatus(final LocalDate date, final List<Room> rooms) {
		HashMap<Room, Boolean> myMap = new HashMap<>();
		for (Room r : rooms) {
			for (Customer c : r.getCustomerList()) {
				myMap.put(r, checkIfRoomIsBusy(date, c));
			}
		}
		return myMap;
	}

	/**
	 * Metodo che prende in ingresso un determinato intervallo di tempo, un
	 * numero che rappresenta i guest che andranno a comporre un determinato
	 * booking e che esegue il controllo sulle stanze disponibili in quel
	 * determinato periodo di tempo con un numero massimo di ospiti >= del
	 * parametro passato in ingresso.
	 * 
	 * @param start
	 *            data di inizio del soggiorno
	 * @param end
	 *            data di fine del soggiorno
	 * @param guestsNumber
	 *            numero di guest del tentativo di booking
	 * @return avaliableRooms le stanze disponibili
	 * 
	 * 
	 */

	public Set<Room> getBookingResults(final LocalDate start, final LocalDate end, final int guestsNumber) {
		Set<Room> avaliableRooms = new HashSet<>();
		if (!Hotel.getInstance().getRoomList().isEmpty()) {
			for (Room r : Hotel.getInstance().getRoomList()) {
				if (r.getMaxGuestsNumber() >= guestsNumber) {
					boolean isBusy;
					if (r.getCustomerList() != null) {
						if (r.getCustomerList().size() > 0) {
							for (Customer c : r.getCustomerList()) {
								isBusy = false;
								if (checkIfRoomIsBusyPeriod(start, end, c)) {
									isBusy = true;
								}
								if (!isBusy) {
									avaliableRooms.add(r);
								}
							}
						} else
							avaliableRooms.add(r);
					} else
						avaliableRooms.add(r);
				}
			}
		}
		if (avaliableRooms.size() > 0)
			return avaliableRooms;
		else
			return null;
	}

	/**
	 * Metodo che prende in ingresso un determinato intervallo di tempo, un
	 * numero che rappresenta i guest che andranno a comporre un determinato
	 * booking e che esegue il controllo sulle stanze disponibili in quel
	 * determinato periodo di tempo con un numero massimo di ospiti >= del
	 * parametro passato in ingresso e un RoomType che andr� a fungere da filtro
	 * aggiuntivo sul checking delle stanze
	 * 
	 * @param start
	 *            data di inizio del soggiorno
	 * @param end
	 *            data di fine del soggiorno
	 * @param guestsNumber
	 *            numero di guest del tentativo di booking
	 * 
	 * @param rt
	 *            il tipo di stanza richiesta dal prenotante
	 * @return avaliableRooms le stanze disponibili
	 * 
	 * 
	 */

	public Set<Room> getFilteredBookingResults(final LocalDate start, final LocalDate end, final int guestsNumber,
			final RoomType rt) {
		Set<Room> avaliableRooms = new HashSet<>();
		if (getBookingResults(start, end, guestsNumber) != null) {
			for (Room r : getBookingResults(start, end, guestsNumber)) {
				if (r.getType().equals(rt)) {
					avaliableRooms.add(r);
				}
			}
		}
		if (avaliableRooms.size() > 0)
			return avaliableRooms;
		else
			return null;
	}

	/**
	 * Metodo che prende in ingresso un intero(numero di stanza) e ritorna un
	 * boolean in base alla presenza o meno di una stanza associata a quel
	 * numero all'interno dell'istanza di Hotel
	 * 
	 * @param roomValue
	 *            l'intero della stanza da cercare
	 * @return true if roomnumber is present, false altrimenti
	 * 
	 */

	public boolean checkIfRoomIsPresent(final int roomValue) {
		if (!Hotel.getInstance().getRoomList().isEmpty()) {
			for (final Room r : Hotel.getInstance().getRoomList()) {
				if (r.getNumber() == roomValue) {
					JOptionPane.showMessageDialog(null, "NUMERO DI STANZA GIA' INSERITO", "ERRORE",
							JOptionPane.ERROR_MESSAGE);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Metodo che prende in ingresso una serie di double ed int che andranno a
	 * popolare le voci del catalogo associato alla nostra istanza di hotel.
	 * 
	 */

	public void saveCatalog(final double highPercentage, final double midPercentage, final double day,
			final double BBOverPrice, final double halfBoardOverPrice, final double fullBoardOverPrice,
			final double premiumOverPrice, final double suiteOverPrice, final double childPercentage,
			final int childAge, final int babyAge) {
		Hotel.getInstance().getCatalog().setAll(highPercentage, midPercentage, day, BBOverPrice, halfBoardOverPrice,
				fullBoardOverPrice, premiumOverPrice, suiteOverPrice, childPercentage, childAge, babyAge);
	}

	/**
	 * PARTE NON FUNZIONANTE
	 */
	public void addBalance(final Object[] values, final boolean isIncoming) {
		Hotel.getInstance().addBalance(Controller.formatLocalDate((String) values[0]), (String) values[1],
				Double.parseDouble((String) values[2]), isIncoming);
	}

	/**
	 * PARTE NON FUNZIONANTE
	 */

	public void removeRow(final String string, final int index, final boolean isIncoming) {
		Hotel.getInstance().removeBalance(string, index, isIncoming);
	}

	/**
	 * Metodo che collega il customer, il booking e la stanza passati in
	 * ingresso dalla view istanziandoli nel model.
	 * 
	 * @param c
	 *            customer(name,surname,cf,birth)
	 * @param b
	 *            booking associato al customer
	 * @param r
	 *            room associata al booking
	 * 
	 */

	public void createBooking(final Customer c, final Booking b, final Room r) {
		r.getCustomerList().add(c);
		b.addActualCustomer(c);
		Hotel.getInstance().addBooking(b);
		Hotel.getInstance().addGuest(c);
	}

	/**
	 * Metodo che aggiunge un extra passato in ingresso ad un booking passato in
	 * ingresso
	 * 
	 * @param extra
	 * @param booking
	 */
	public void addExtraToBooking(final Booking b, final String extra) {
		for (final Extra e : Hotel.getInstance().getExtraList()) {
			if (e.getName().equals(extra)) {
				b.addExtra(e);
			}
		}
	}

	/**
	 * Metodo che viene fatto runnare nel main per settare ad ogni avvio gli
	 * actualcustomers di stanze che presentano una prenotazione, esegue il
	 * check sulla data odierna e se vi � un customer nella customerlist della
	 * room che ha un booking con start = LocalDate.now() setta per quella room
	 * quello specifico customer come actualCustomer della room
	 * 
	 */

	public void computeBusyRooms() {
		for (Room r : Hotel.getInstance().getRoomList()) {
			if (r.isBusy()) {
				if (r.getActualCustomer().getBooking().getEndDate().isEqual(LocalDate.now())) {
					r.setActualCustomer(null);
				}
			} else {
				if (r.getCustomerList() != null) {
					if (r.getCustomerList().size() > 0) {
						for (Customer c : r.getCustomerList()) {
							if (c.getBooking().getStartDate().isEqual(LocalDate.now())) {
								r.setActualCustomer(c);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Metodo che prende in ingresso due stringe necessarie all'aggiunta di un
	 * piatto all'interno del dailyMenu dell'hotel
	 * 
	 * @param type
	 *            la stringa tipo di piatto(antipasti, primi,secondi..)
	 * @param text
	 *            la stringa di testo che denomina il nome del piatto da
	 *            aggiungere nel menu
	 * 
	 */

	public void addMenuVoice(final String type, final String text) {
		Hotel.getInstance().getDailyMenu().addPiatto(type, text);
	}

	/**
	 * Metodo statico usato all'interno della creazione booking per la ricerca
	 * della migliore stanza ( numero di ospiti pi� vicino al maxguestsnumber
	 * della room), che verr� poi utilizzata per completare il booking
	 * 
	 * @param avaliableRooms
	 *            il set di stanze filtrate in base ai parametri del
	 *            booking(tipostanza e maxguests) tra le quali andare a cercare
	 *            la bestroom
	 * @return
	 */

	public static Room selectOptimalRoom(Set<Room> avaliableRooms) {
		Room room = null;
		for (Room r : avaliableRooms) {
			if (room == null) {
				room = r;
			} else if (r.getMaxGuestsNumber() < room.getMaxGuestsNumber()) {
				room = r;
			}
		}
		return room;
	}

	/**
	 * Metodo statico usato sostanzialmente ovunque che prende in ingresso una
	 * stringa di tipo gg/mm/yyyy e restituisce una LocalDate (LocalDate di
	 * default prende in ingresso una stringa del tipo mm-gg-yyyy) Fa
	 * visualizzare un optionpane nel caso la stringa in input sia mal
	 * formattata e ritorna nullin quel caso
	 * 
	 * @param date
	 *            la stringa formattata
	 * @return ld la localdate nel caso la stringa di testo sia ben formattata,
	 *         null in caso contrario
	 */

	public static LocalDate formatLocalDate(final String date) {
		try {
			LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			return ld;
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(null, "FORMATO CORRETTO DATA dd/mm/yyyy", Controller.error,
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	/**
	 * Metodo statico usato come utility per controllare se la stringa passata
	 * in input sia nulla o vuota
	 * 
	 * 
	 * @param string
	 * @return true nel caso tutto vada a buon fine, false nel caso la stringa
	 *         sia nulla/vuota
	 */

	public static boolean checkIfNull(final String string) {
		if (string == null || string.isEmpty()) {
			JOptionPane.showMessageDialog(null, Controller.error, Controller.nullString, JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Metodo statico che controlla carattere per carattere la stringa passata
	 * in ingresso e ritorna un booleano vero nel caso la stringa contenga un
	 * numero, falso altrimenti
	 * 
	 * @param string
	 * @return true if containsnumber, false altrimenti
	 */

	public static boolean checkIfContainsNumber(final String string) {
		if (checkIfNull(string)) {
			for (char c : string.toCharArray()) {
				if (Character.isDigit(c)) {
					JOptionPane.showMessageDialog(null, Controller.error, Controller.stringContainsNumber,
							JOptionPane.ERROR_MESSAGE);
					return true;
				}
			}
		} else
			return false;
		return false;
	}
	
	/**
	 * Metodo statico per il check su una stringa passata in input, ritorna un booleano
	 * 
	 * @param string
	 * @return true nel caso la stringa passata in input sia un numero (check su double), false altrimenti
	 */

	public static boolean checkIfIsNumber(final String string) {
		if (checkIfNull(string)) {
			try {
				Double.parseDouble(string);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, Controller.error, Controller.isNotNumber,
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		} else
			return false;
	}

	public static boolean checkIfNumberIsCorrect(final String number, final double constraint,
			final boolean higherThanConstraint) {
		if (checkIfIsNumber(number)) {
			Double numberz = Double.parseDouble(number);
			if (higherThanConstraint && numberz < constraint || !higherThanConstraint && numberz > constraint) {
				return false;
			}

		} else
			return false;
		return true;
	}
	
	/** 
	 * Metodo per la formattazione di una localdate, si � rivelato indispensabile in quanto il formattatore dd/mm/yyyy
	 * nel caso prenda in input valori come 11/9/2001 lancia una eccezione (11/09/2001 sarebbe la formattazione corretta)
	 * @param date
	 * @return string la stringa formattata
	 */

	public static String fromLocalDateToString(final LocalDate date) {
		StringBuilder sb = new StringBuilder();
		if (date.getDayOfMonth() < 10) {
			sb.append("0" + date.getDayOfMonth());
		} else
			sb.append(date.getDayOfMonth());
		if (date.getMonthValue() < 10) {
			sb.append("/0" + date.getMonthValue());
		} else
			sb.append("/" + date.getMonthValue());
		sb.append("/" + date.getYear());
		return new String(sb);
	}
	
	/*
	 * Metodo richiamato alla chiusura del mainframe, con la consequenziale uscita dal programma
	 * Salva i dati presenti nel model.
	 * 
	 */

	public static void saveModel() {
		Hotel.saveInstance();
	}
	
	/*
	 * Metodo statico per il ritorno del myInstance, usato per il Singleton.
	 */

	public static Controller getInstance() {
		if (myInstance == null) {
			myInstance = new Controller();
		}
		return myInstance;
	}

}
