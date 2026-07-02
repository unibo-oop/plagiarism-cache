package model.riepilogo;

public interface ModelRiepilogo {

	/**
	 * @return the name taken from the customer's reservation
	 */
	String getNome();
	
	/**
	 * @return the surname taken from the customer's reservation
	 */
	String getCognome();
	
	/**
	 * @return the email taken from the customer's reservation
	 */
	String getEmail();
	
	/**
	 * @return the phone number taken from the customer's reservation
	 */
	String getTelefono();
	
	/**
	 * @return the number of seats occupied by the customer
	 */
	String getPosti();
	
	/**
	 * @return the customer's reservation code
	 */
	String getCodice();
	
}
