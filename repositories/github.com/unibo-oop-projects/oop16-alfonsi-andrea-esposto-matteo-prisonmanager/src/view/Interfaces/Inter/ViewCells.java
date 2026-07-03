package view.Interfaces.Inter;

import javax.swing.JTable;

import controller.Implementations.ViewCellsControllerImpl.BackListener;

public interface ViewCells {

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
	  * crea la tabella in cui mettere le celle
	  * @param table
	  */
	 public void createTable(JTable table);

}
