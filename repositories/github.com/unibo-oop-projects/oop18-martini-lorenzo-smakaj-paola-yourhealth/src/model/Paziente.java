package model;

/**
 * 
 * Interface for Patient.
 *
 */

public interface Paziente extends Persona {
	/**
	 * fiscal code getter.
	 * 
	 * @return patient's fiscal code
	 */
	String getCodicefiscale();

	/**
	 * address getter.
	 * 
	 * @return patient's address
	 */
	String getResidenza();
}
