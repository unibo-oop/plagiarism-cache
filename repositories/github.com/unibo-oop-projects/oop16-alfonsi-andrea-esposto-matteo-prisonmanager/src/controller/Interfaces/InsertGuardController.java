package controller.Interfaces;

import model.Interfaces.Guard;

public interface InsertGuardController {

	/**
	 * inserisce una nuova guardia
	 */
	public void insertGuard();
	
	/**
	 * controlla se la guardia è stata implementata correttamente
	 * @param g la guardia
	 * @return true se la guardia è stata implementata male
	 */
	public boolean isSomethingEmpty(Guard g);
}
