/*
 * Classe che modella un ospite d'albergo.
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

import model.interfaces.IGuest;

// TODO: Auto-generated Javadoc
/**
 * The Class Guest.
 */
public class Guest implements IGuest, Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4420769913440788548L;
	
	/** The name. */
	private final String name;
	
	/** The surname. */
	private final String surname;
	
	/** The cf. */
	private final String CF;
	
	/** The birth. */
	private final LocalDate birth;

	/**
	 * Instantiates a new guest.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param CF the cf
	 * @param birth the birth
	 */
	public Guest(final String name, final String surname, final String CF, final LocalDate birth) {
		this.name = name;
		this.surname = surname;
		this.CF = CF;
		this.birth = birth;
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IGuest#getName()
	 */
	public String getName() {
		return new String(this.name);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IGuest#getSurname()
	 */
	public String getSurname() {
		return new String(this.surname);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IGuest#getCF()
	 */
	public String getCF() {
		return new String(this.CF);
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IGuest#getBirthDate()
	 */
	public LocalDate getBirthDate() {
		return this.birth;
	}

	/* (non-Javadoc)
	 * @see model.interfaces.IGuest#getAge()
	 */
	public int getAge() {
		return Calendar.getInstance().get(Calendar.YEAR) - birth.getYear();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[GUEST]: " + this.getName() + " " + this.getSurname() + "\n[BIRTH, CF]: " + this.getBirthDate() + " "
				+ this.getCF();
	}
}
