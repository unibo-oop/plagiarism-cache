package view.Interfaces.Inter;

import java.util.Date;
import java.util.List;

import controller.Implementations.InsertPrisonerControllerImpl.AddCrimeListener;
import controller.Implementations.InsertPrisonerControllerImpl.BackListener;
import controller.Implementations.InsertPrisonerControllerImpl.InsertPrisonerListener;

public interface InsertPrisoner {

	/**
	 * aggiunge l'insert prisoner listener
	 * @param addPrisonerListener
	 */
	public void addInsertPrisonerListener(InsertPrisonerListener addPrisonerListener);
	
	/**
	 * aggiunge il back listener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);
	
	/**
	 * ritorna l'id del prigioniero
	 * @return l'id
	 */
	public int getPrisonerID1();

	/**
	 * restituisce nome prigioniero
	 * @return il nome
	 */
	public String getName1();

	/**
	 * restituisce il cognome del prigioniero
	 * @return il cognome
	 */
	public String getSurname1();

	/**
	 * ritorna la data di inizio di prigionia
	 * @return data di inizio di prigionia
	 */
	public Date getStart1();

	/**
	 * ritorna la data di fine della prigionia
	 * @return data di fine della prigionia
	 */
	public Date getEnd1();
	
	/**
	 * ritorna data di nascita
	 * @return data di nascita
	 */
	public Date getBirth1();
	
	/**
	 * ritorna l'id della cella
	 * @return id della cella
	 */
	public int getCellID();
	
	/**
	 * mostra un messaggio
	 * @param error il messaggio
	 */
	public void displayErrorMessage(String error);
	
	/**
	 * ritorna il rank
	 * @return
	 */
	public int getRank();
	
		/**
		 * imposta la lista dei crimini nella textarea
		 * @param list la lista dei crimini
		 */
	public void setList(List<String>list);
	
	/**
	 * ritorna la lista dei crimini presente nella textarea
	 * @return la lista di crimini
	 */
	public List<String> getList();
	
	/**
	 * ritorna il crimine presente nella combobox
	 * @return
	 */
	public String getCombo();
	
	/**
	 * aggiunge l'add crime listener
	 * @param addCrimeListener
	 */
	public void addAddCrimeListener(AddCrimeListener addCrimeListener);
}
