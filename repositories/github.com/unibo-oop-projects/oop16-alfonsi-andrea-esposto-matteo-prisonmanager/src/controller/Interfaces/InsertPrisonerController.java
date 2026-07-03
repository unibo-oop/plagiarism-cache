package controller.Interfaces;

import model.Interfaces.Prisoner;

public interface InsertPrisonerController {

	/**
	 * controlla che gli spazi di nome e cognome non siano vuoti
	 * @param p prigioniero da controllare
	 * @return true se un campo è vuoto
	 */
	public boolean isSomethingEmpty(Prisoner p);
	
	/**
	 * inserisce un prigioniero
	 */
	public void insertPrisoner();
}
