/**
 * @author Ceccarelli
 * */
package view;

import view.observer.NorthPanelObserver;

public interface NorthPanel {
	/**
	 * set per l'observer del northPanel
	 * 
	 * @param observer
	 */
	void attachObserver(NorthPanelObserver observer);
	/**
	 * Pulisce tutte le stringhe presenti nel panel
	 */
	void clearPanel();
	/**
	 * Permette di visualizzare il nome ed il cognome del dipendete 
	 * che ha effettuato la login in alto a destra
	 * 
	 * @param nome
	 * @param cognome
	 */
	void displayLoggedEmployee(String nome, String cognome);
	/**
	 * cambia lo stato della login a logout e viceversa 
	 */
	void changeLogStatus(Boolean logged);
}
