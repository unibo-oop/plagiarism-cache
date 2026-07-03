package view.Interfaces.Inter;

import controller.Implementations.ViewGuardControllerImpl.BackListener;
import controller.Implementations.ViewGuardControllerImpl.ViewGuardListener;

public interface ViewGuard {

	/**
	 * ritorna il rank
	 * @return il rank
	 */
	public int getRank();
	
	/**
	 * aggiunge il view listener
	 * @param viewListener
	 */
	public void addViewListener(ViewGuardListener viewListener);

	/**
	 * aggiunge il back listener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);
	
	/**
	 * mostra un messaggio
	 * @param error il messaggio
	 */
	public void displayErrorMessage(String error);
	
	/**
	 * ritorna l'id inserito
	 * @return l'id
	 */
	public int getID();
	
	/**
	 * imposta il nome nella label predisposta
	 * @param name il nome
	 */
	public void setName(String name);
	
	/**
	 * imposta il cognome nella label predisposta
	 * @param surname il cognome
	 */
	public void setSurname(String surname);
	
	/**
	 * imposta la data di compleanno nella label predisposta
	 * @param birth la data di nascita
	 */
	public void setBirth(String birth);
	
	/**
	 * imposta il rank nella label predisposta
	 * @param rank1 il rank della guardia
	 */
	public void setRank(String rank1);
	
	/**
	 * imposta il numero di telefono nella label predisposta
	 * @param telephone numero di telefono
	 */
	public void setTelephone(String telephone);
}
