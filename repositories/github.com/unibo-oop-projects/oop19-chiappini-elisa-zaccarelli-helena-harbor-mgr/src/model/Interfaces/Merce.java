package model.Interfaces;


/**
 * Interfaccia tipologie merce 
 * @author Helena Zaccarelli
 */

public interface Merce {

	/**
	 * Metodo che ritorna il codice univoco di una tipologia di merce
	 * @return
	 */
	public int getCod();
	
	/**
	 * Metodo che setta il codice univoco di una tipologia di merce
	 * @param codice
	 */
	public void setCod(int codice);
	
	/**
	 * Metodo che ritorna il nome di una tipologia di merce
	 * @return
	 */
	public String getNome();
}
