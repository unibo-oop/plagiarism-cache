package view.Interfaces.Inter;

import controller.Implementations.RemoveGuardControllerImpl.BackListener;
import controller.Implementations.RemoveGuardControllerImpl.RemoveGuardListener;

public interface RemoveGuard {

	/**
	 * ritorna l'id
	 * @return
	 */
	public int getID();
	
	/**
	 * mostra un messaggio
	 * @param error il messaggio
	 */
	public void displayErrorMessage(String error);

	/**
	 * ritona il rank
	 * @return rank
	 */
	public int getRank();
	
	/**
	 * aggiunge il remove guard listener
	 * @param removeGuardListener
	 */
	public void addRemoveGuardListener(RemoveGuardListener removeGuardListener);

	/**
	 * aggiunge il back listener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);
}
