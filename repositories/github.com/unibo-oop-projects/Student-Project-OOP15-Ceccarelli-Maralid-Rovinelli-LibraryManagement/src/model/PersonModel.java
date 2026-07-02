package model;

/**
 * 
 * @author Mattia.Rovinelli
 *
 */
public interface PersonModel {
	/**
	 * set the taxCode
	 * 
	 * @param taxCode
	 */
	public void setTaxCode(String taxCode);

	/**
	 * this method set the name
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * this method set a surname
	 * 
	 * @param surname
	 */
	public void setSurname(String surname);

	/**
	 * this method set a email
	 * 
	 * @param email
	 */
	public void setEmail(String email);

	/**
	 * this method return a taxcode
	 * 
	 * @return String email
	 */
	public String getTaxCode();

	/**
	 * this method get a surname
	 * 
	 * @return string surname
	 */
	public String getSurname();

	/**
	 * this method return a name
	 * 
	 * @return String name
	 */
	public String getName();

	/**
	 * this method return a mail
	 * 
	 * @return String email
	 */
	public String getEmail();

}
