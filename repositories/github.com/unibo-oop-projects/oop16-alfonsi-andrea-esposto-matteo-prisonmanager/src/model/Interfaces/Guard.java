package model.Interfaces;

/**
 * interfaccia di una guardia
 */
public interface Guard extends Person{

	/**
	 * Metodo che imposta la password
	 * @param newPass new password
	 * @return
	*/
	public void setPassword(String newPass);
	
	/**
	 * Metodo che ritorna la password
	 * @return the password
	*/
	public String getPassword();
	
	/**
	 * Metodo che ritorna l username
	 * @return the username
	*/
	public int getUsername(); 
	
	/**
	 *metodo che ritorna il numero di telefono
	 *@return the telephone number 
	 */
	public String getTelephoneNumber();
	
	/**
	 *metodo che imposta il numero di telefono
	 *@return  
	 */
	public void setTelephoneNumber(String telephoneNumber);

	/**
	 *metodo che ritorna il grado della guardia
	 *@return the rank
	 */
	public int getRank();

	/**
	 *metodo che imposta il grado della guardia
	 *@return  
	 */
	public void setRank(int rank);

	/**
	 * metodo che setta l'id della guardia
	 * @param idGuardia i guardia
	 */
	void setID(int idGuardia);

	/**
	 * metodo che restituisce l'id della guardia
	 * @return id della guardia
	 */
	int getID();
	
}
