package model;

import java.util.Date;

public interface EmployeeModel {
	/**
	 * this method return a date of Birth
	 * 
	 * @return date
	 */
	public Date getDateOfBirth();

	/**
	 * this method return the date of hire
	 * 
	 * @return Date
	 */
	public Date getHireDate();

	/**
	 * this method return the address of employee
	 * 
	 * @return String
	 */
	public String getAddress();

	/**
	 * this method return the username
	 * 
	 * @return
	 */
	public String getUsername();

	/**
	 * this method return the password
	 * 
	 * @return string
	 */
	public char[] getPassword();

	/**
	 * method for set a date of birth
	 * 
	 * @param date
	 */
	public void setDateOfBirth(Date date);

	/**
	 * method for set the hire date
	 * 
	 * @param hireDate
	 */
	public void setHireDate(Date hireDate);

	/**
	 * method for set the address
	 * 
	 * @param address
	 */
	public void setAddress(String address);

	/**
	 * metod for set the username
	 * 
	 * @param username
	 */
	public void setUsername(String username);

	/**
	 * method for set the password
	 * 
	 * @param password
	 */
	public void setPassword(char[] password);

}
