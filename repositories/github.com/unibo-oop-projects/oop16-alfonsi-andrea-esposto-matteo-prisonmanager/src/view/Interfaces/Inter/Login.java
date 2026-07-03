package view.Interfaces.Inter;

import controller.Implementations.LoginControllerImpl.LoginListener;

public interface Login {

	/**
	 * ritorna l'username
	 * @return l'username
	 */
	public String getUsername();
	
	/**
	 * ritorna la password
	 * @return la password
	 */
	public String getPassword();
	
	/**
	 * mostra un messaggio
	 * @param error il messaggio
	 */
	public void displayErrorMessage(String error);
	
	/**
	 * aggiunge il login listener
	 * @param loginListener
	 */
	public void addLoginListener(LoginListener loginListener);
}
