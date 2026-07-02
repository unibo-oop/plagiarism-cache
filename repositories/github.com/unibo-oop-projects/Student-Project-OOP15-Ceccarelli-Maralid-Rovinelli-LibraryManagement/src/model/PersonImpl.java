package model;

import java.io.Serializable;

/**
 * this class is a Person and is abstract class. It represents the
 * characteristics that all people have within the program.
 * 
 * @author Mattia.Rovinelli
 *
 */
public abstract class PersonImpl implements Serializable, PersonModel {

	private static final long serialVersionUID = 1L;
	protected String taxCode;
	protected String name;
	protected String surname;
	protected String email;

	public PersonImpl() {

	}

	public PersonImpl(String taxCode, String name, String surname, String email) {
		this.taxCode = taxCode;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
