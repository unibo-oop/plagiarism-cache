package model.Interfaces;


/**
 * Interfaccia di porto
 * @author Helena Zaccarelli
 */

public interface Porto {

	/**
	 * Metodo che ritorna il codice univoco del porto
	 * @return
	 */
	public int getCod();
	
	/**
	 * Metodo che setta il codice univoco del porto
	 * @param codice
	 */
	public void setCod(int codice);
	
	/**
	 * Metodo che ritorna il nome del porto
	 * @return
	 */
	public String getNome();
	
	/**
	 * Metodo che ritorna la nazione in cui si trova il porto
	 * @return
	 */
	public String getNazione();
}
