package model;

import java.io.Serializable;

/**
 * This class represents a customer of library. The customer is composed of the
 * data of the abstract class and its data(telefon number).
 * 
 * @author mattia.rovinelli
 *
 */
public class CustomerImpl extends PersonImpl implements CustomerModel, Serializable {

	private static final long serialVersionUID = 1L;
	private long telefonCustomers;

	public CustomerImpl(String taxCode, String name, String surname, String email, long telefonCustomers) {
		super(taxCode, name, surname, email);
		this.telefonCustomers = telefonCustomers;
	}

	public long getTelefonCustomers() {
		return telefonCustomers;
	}

	public void setTelefonCusomers(long telefon) {
		this.telefonCustomers = telefon;

	}

}
