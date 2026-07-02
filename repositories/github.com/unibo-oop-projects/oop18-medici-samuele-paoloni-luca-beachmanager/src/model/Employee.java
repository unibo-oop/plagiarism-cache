package model;

import java.util.Date;

public class Employee {

	// Dati anagrafici
	private String name;
	private String surname;
	private Date birthday;

	/**
	 * Costruttore per la classe Dipendente
	 * @param name Nome del dipendente
	 * @param surname Cognome del dipendente
	 * @param birthday Data di nascita
	 */
	public Employee(String name, String surname, Date birthday) {
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
	}

	// Setter e getter

	/**
	 * Getter del nome
	 * @return nome del dipendete
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter del nome
	 * @param name nome del dipendente
 	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Getter del nome
	 * @return nome del dipendete
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Setter del cognome
	 * @param surname cognome del dipendente
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Getter della data di nascita
	 * @param birthday data di nascita del dipendente
 	 */
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * Ritorna il nome intero 
	 * @return nome e cognome
	 */
	public String getFullName() {
		return this.name + " " + this.surname;
	}

}
