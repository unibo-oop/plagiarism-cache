package view.Interfaces.Inter;

import controller.Implementations.AddVisitorsControllerImpl.BackListener;
import controller.Implementations.AddVisitorsControllerImpl.InsertListener;
import model.Implementations.VisitorImpl;

public interface AddVisitors {

	/**
	 * ritorna il visitatore inserito nella view
	 * @return the visitor
	 */
	public VisitorImpl getVisitor();
	
	/**
	 * ritorna il rank
	 * @return il rank
	 */
	public int getRank();
	
	/**
	 * mostra un messaggio
	 * @param error il messaggio
	 */
	public void displayErrorMessage(String error);
	
	/**
	 * aggiunger il backLsitener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);
	
	/**
	 * aggiunge l'insert visitor listener
	 * @param insertListener
	 */
	public void addInsertVisitorListener(InsertListener insertListener);
}
