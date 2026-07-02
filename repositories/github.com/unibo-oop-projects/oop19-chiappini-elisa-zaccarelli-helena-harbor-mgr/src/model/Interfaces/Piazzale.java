package model.Interfaces;


/**
 * Interfaccia del piazzale di deposito merci del porto
 * @author Helena Zaccarelli
 */

public interface Piazzale {

	/**
	 * Metodo che ritorna lo spazio totale presente nel piazzale (indicato in spazi/postazioni)
	 * @return
	 */
	public int getSpazioTot();
	
	/**
	 * Metodo che ritorna lo spazio libero in piazzale (spazi/postazioni)
	 * @return
	 */
	public int getSpazioLibero();
	
	/**
	 * Metodo che setta lo spazio libero in piazzale (spazi/postazioni)
	 * @param spazioLibero 
	 */
	public void setSpazioLibero(int spazioLibero);
}
