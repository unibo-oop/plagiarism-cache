package view.Interfaces.Inter;


import controller.Implementations.RemovePrisonerControllerImpl.BackListener;
import controller.Implementations.RemovePrisonerControllerImpl.RemoveListener;

public interface RemovePrisoner {

	/**
	 * aggiunge il remove listener
	 * @param removeListener
	 */
	public void addRemoveListener(RemoveListener removeListener);

	/**
	 * aggiunge il back listener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);

	/**
	 * ritorna l'id del prigioniero
	 * @return id del prigioniero
	 */
	public int getID();
	
	/**
	 * mostra un messaggio
	 * @param error il messaggio
	 */
	public void displayErrorMessage(String error);
	
	/**
	 * ritorna il rank
	 * @return il rank
	 */
	public int getRank();
}
