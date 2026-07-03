package view.Interfaces.Inter;

import javax.swing.JTable;

import controller.Implementations.ViewVisitorsControllerImpl.BackListener;

public interface ViewVisitors {

	/**
	 * ritorna il rank
	 * @return il rank
	 */
	 public int getRank();
	 
	 /**
	  * aggiunge il back listener
	  * @param backListener
	  */
	 public void addBackListener(BackListener backListener);
	 
	 /**
	  * crea la tabella in cui mettere i visitatori e la imposta
	  * @param table tabella contenente i dati
	  */
	 public void createTable(JTable table);
}
