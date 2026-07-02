package model;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents a employee of library. The customer is composed of the
 * data of the abstract class and its data(date of birth,hire date).
 * 
 * @author mattia.rovinelli
 *
 */

public class EmployeeImpl extends PersonImpl implements EmployeeModel, Serializable {

	private static final long serialVersionUID = 1L;
	private Date dateOfBirth;
	private Date hireDate;
	private String address;
	private String username;
	private char[] password;

	public EmployeeImpl() {

	}

	public EmployeeImpl(String taxCode, String name, String surname, String email, Date dateOfBirth, Date hireDate,
			String address, String username, char[] password) {
		super(taxCode, name, surname, email);

		this.dateOfBirth = dateOfBirth;
		this.hireDate = hireDate;
		this.address = address;
		this.username = username;
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public String getAddress() {
		return address;
	}

	public String getUsername() {
		return username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setDateOfBirth(Date date) {
		this.dateOfBirth = date;

	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public void setAddress(String address) {
		this.address = address;

	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

}
