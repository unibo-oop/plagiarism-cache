package model.Interfaces;

public interface Movement {

	/**
	*ritorna la descrizione del movimento
	*@return description of the movement
	*/
	public String getDescr();
	
	/**
	*setta la descrizione del movimento
	*@param description of the movement
	*/
	public void setDescr(String descr);
	
	/**
	*ritorna la quantita del movimento
	*@return amount of the movement
	*/
	public double getAmount();
	
	/**
	*setta la quantità del movimento
	*@param amount of the movement
	*/
	public void setAmount(double amount);
	
	/**
	 * ritorna se il movimento è positivo o negativo
	 * @return the sign of the movement (+ or -)
	 */
	public char getChar();
	
	/**
	 * ritorna la data del movimento
	 * @return the date of the movement
	 */
	public String getData1();

}
