package view.Interfaces.Inter;

import controller.Implementations.InsertGuardControllerImpl.BackListener;
import controller.Implementations.InsertGuardControllerImpl.InsertListener;
import model.Interfaces.Guard;

public interface InsertGuard {

	/**
	 * metodo che ritorna la guardia inserita
	 * @return la guardia
	 */
	public Guard getGuard();
	
	/**
	 * restituisce il rank
	 * @return
	 */
	public int getRank();
	
	/**
	 * aggiunge l' insert listener
	 * @param insertListener
	 */
	public void addInsertListener(InsertListener insertListener);
	
	/**
	 * mostra un messaggio
	 * @param error il messaggio
	 */
	public void displayErrorMessage(String error);
	
	/**
	 * aggiunge il back listener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);
}
