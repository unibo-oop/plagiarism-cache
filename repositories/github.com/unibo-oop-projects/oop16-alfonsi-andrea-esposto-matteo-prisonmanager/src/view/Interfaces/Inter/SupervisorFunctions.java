package view.Interfaces.Inter;

import controller.Implementations.SupervisorControllerImpl.BackListener;
import controller.Implementations.SupervisorControllerImpl.InsertGuardListener;
import controller.Implementations.SupervisorControllerImpl.RemoveGuardListener;
import controller.Implementations.SupervisorControllerImpl.ShowPrisonersListener;
import controller.Implementations.SupervisorControllerImpl.ViewGuardListener;

public interface SupervisorFunctions {

	/**
	 * ritorna il rank
	 * @return rank
	 */
	public int getRank();
	
	/**
	 * aggiunge il back listener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);

	/**
	 * aggiunge lo show prisoners listener
	 * @param showPrisonersListeners
	 */
	public void addShowPrisonersListener(ShowPrisonersListener showPrisonersListeners);
	
	/**
	 * aggiunge l'insert guard listener
	 * @param insertGaurdListener
	 */
	public void addInsertGuardListener(InsertGuardListener insertGaurdListener);
	
	/**
	 * aggiunge il remove guard listener
	 * @param removeListener
	 */
	public void addRemoveGuardListener(RemoveGuardListener removeListener);
	
	/**
	 * aggiunge il view guard listener
	 * @param viewListener
	 */
	public void addviewGuardListener(ViewGuardListener viewListener);
}
