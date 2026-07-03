package model.interfaces;

import java.time.LocalDate;
import java.util.List;

import model.Extra;
import model.Guest;
import model.Room;

public interface IBooking {


	/**
	 * Metodo che ritorna il numero di stanza associato a questa istanza di
	 * booking.
	 * 
	 * @return room
	 */

	public Room getRoom();

	/**
	 * Metodo che ritorna il prezzo totale (non tenendo conto del
	 * pagato/caparra) del booking
	 * 
	 * @return price
	 */

	public Double getPrice();

	/**
	 * Metodo che ritorna la data d'inizio del booking
	 * 
	 */

	public LocalDate getStartDate();

	/**
	 * Metodo che ritorna la data di checkout
	 */

	public LocalDate getEndDate();

	/**
	 * Metodo che ritorna la lista di guests(incluso il customer) presenti in
	 * quel booking, questo metodo è pensato per l'inserimento in un secondo
	 * momento dei dati anagrafici relativi a tutti gli ospiti ( in genere alla
	 * prenotazione si prendono le generalità del customer e al checkin quelle
	 * di tutti gli ospiti.
	 * 
	 */

	public List<Guest> getGuestsList();

	/**
	 * Metodo che imposta il prezzo del booking
	 * 
	 */

	public void setPrice(final double price);

	/**
	 * Metodo che ritorna un booleano true nel caso l'amount pagato sia
	 * equivalente al getPrice(),false altrimenti
	 */

	public boolean hasPayed();

	/**
	 * Metodo che ritorna un double che sta ad indicare l'ammontare che il
	 * customer deve ancora pagare.
	 * 
	 */

	public double getToPay();

	/**
	 * Metodo per aggiungere o sottrarre una determinata quantità al price
	 * @param amount
	 */

	public void changeAmount(final double amount);
	
	/**
	 * Metodo per tenere traccia di un pagamento(anticipo,caparra,parziale,totale)
	 * prende in ingresso un double e lo va ad aggiungere al payed.
	 * @param amount
	 * 
	 */

	public void pay(final double amount);
	
	
	/**
	 * Metodo che prende in ingresso un extra e lo aggiunge alla lista degli extra richiesti da questo specifico booking.
	 * @param extra
	 */

	public void addExtra(final Extra extra);
	
	/**
	 * Metodo per aggiornare la data relativa all'ultimo pasto(ieri o oggi), utile nel metodo canEat per distinguere quelle stanze
	 * che possono ancora effettuare un'ordinazione per pranzo/cena in base al booking(solo mezza pensione o pensione completa).
	 * 
	 */

	public void updateEatenDate();
	
	/**
	 * Metodo che ritorna se i bookers hanno già effettuato un pasto oggi.
	 * 
	 */

	public boolean hasEatenToday();
	
	/**
	 * 
	 * Metodo per indicare che un pasto è stato appena consumato.
	 */

	public void eat();
	
	/**
	 * Metodo che controlla se il booking può mangiare oggi,effettua il check sul typebooking (solo mezza e completa possono mangiare)
	 * e se un pasto è già stato consumato (caso in cui si ha già mangiato a pranzo) a cena ci può mangiare solo la completa.
	 * 
	 * 
	 * @return true if is possible to eat, false altrimenti.
	 */

	public boolean canEat();

	public String toString();

}
