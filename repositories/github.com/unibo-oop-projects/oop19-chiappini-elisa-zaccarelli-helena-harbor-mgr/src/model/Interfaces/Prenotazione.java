package model.Interfaces;


/**
 * Interfaccia di prenotazione
 * @author Helena Zaccarelli
 */

public interface Prenotazione {
	
	/**
	 * Metodo che ritorna il codice univoco della prenotazione
	 * @return
	 */
	public int getCodice();
	
	/**
	 * Metodo che setta il codice univoco della prenotazione
	 * @param codice
	 */
	public void setCodice(int codice);
	
	/**
	 * Metodo che ritorna il codice del viaggio
	 * @return
	 */
	public int getId();
	
	/**
	 * Metodo che ritorna il tipo di merce da caricare (containers, rotabili, etc.)
	 * @return
	 */
	public String getTipo();
	
	/**
	 * Metodo che ritorna il numero di colli di merce da caricare
	 * @return
	 */
	public int getColli();
	
	/**
	 * Metodo che ritorna il peso totale della merce da caricare (espresso in tonnellate)
	 * @return
	 */
	public int getPeso();
}
