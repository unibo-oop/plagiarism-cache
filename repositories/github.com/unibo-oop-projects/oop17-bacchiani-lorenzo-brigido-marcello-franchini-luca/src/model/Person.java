package model;

import java.time.LocalDate;

public interface Person {
	/**
	 * 
	 * @return person's name.
	 */
	String getName();
	/**
	 * 
	 * @return person's surname.
	 */
	String getSurname();
	/**
	 * 
	 * @return person's fiscalcode.
	 */
	String getFiscalCode();
	/**
	 * 
	 * @return person's birthday date.
	 */
	LocalDate getBirthdayDate();
	/**
	 * 
	 * @return persons's birth place.
	 */
	String getBirthPlace();
	/**
	 * 
	 * @return person's residency.
	 */
	String getResidency();
	/**
	 * 
	 * @return patient's sex;
	 */
	String getSex();
}
