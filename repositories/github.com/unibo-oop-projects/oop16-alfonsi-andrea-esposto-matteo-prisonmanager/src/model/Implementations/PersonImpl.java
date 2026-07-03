package model.Implementations;

import java.io.Serializable;
import java.util.Date;

import model.Interfaces.Person;

/**
 * implementazione di una persona
 */
public abstract class PersonImpl implements Serializable, Person{
	
	private static final long serialVersionUID = -6542753884631178660L;
	
	private String name;
	private String surname;
	private Date birthDate;
	
	/**
	 * Instantiates a new person.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param birth the birth 
	 */
	public PersonImpl(String name, String surname, Date birthDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "PersonImpl [name=" + this.getName() + ", surname=" + this.getSurname() + ", birthDate=" + this.getBirthDate() + "]";
	}
	
	
}
