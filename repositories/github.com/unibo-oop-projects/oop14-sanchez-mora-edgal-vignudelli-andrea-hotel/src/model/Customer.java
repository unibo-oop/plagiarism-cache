/*
 * Classe che modella un customer, si differenzia da guest per la presenza di un'istanza booking all'interno dell'oggetto.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

import model.interfaces.IGuest;

// TODO: Auto-generated Javadoc
/**
 * The Class Customer.
 */
public class Customer extends Guest implements IGuest, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3219032739727542941L;

	/** The booking. */
	private Booking booking;

	/**
	 * Instantiates a new customer.
	 *
	 * @param name
	 *            the name
	 * @param surname
	 *            the surname
	 * @param CF
	 *            the cf
	 * @param birth
	 *            the birth
	 * @param booking
	 *            the booking
	 */
	public Customer(final String name, final String surname, final String CF, final LocalDate birth,
			final Booking booking) {
		super(name, surname, CF, birth);
		this.booking = booking;
	}

	/**
	 * Gets the booking.
	 *
	 * @return the booking
	 */
	public Booking getBooking() {
		return this.booking;
	}

	/**
	 * Sets the booking.
	 *
	 * @param booking
	 *            the new booking
	 */
	public void setBooking(final Booking booking) {
		this.booking = booking;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Guest#toString()
	 */
	public String toString() {
		return "[CUSTOMER]: " + this.getName() + " " + this.getSurname() + "\n[BIRTH, CF]: " + this.getBirthDate() + " "
				+ this.getCF();
	}

	/**
	 * To string total.
	 *
	 * @return the string
	 */
	public String toStringTotal() {
		return this.toString() + "\n" + this.getBooking().toString();
	}
}
