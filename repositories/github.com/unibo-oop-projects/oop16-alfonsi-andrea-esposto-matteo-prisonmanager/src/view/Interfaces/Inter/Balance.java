package view.Interfaces.Inter;

import javax.swing.JTable;

import controller.Implementations.BalanceControllerImpl.BackListener;

public interface Balance {

    /**
     * metodo che crea la tabella del bilancio
     * @param table tabella vuota
     */
	public void createTable(JTable table);
	
	/**
	 * imposta la label del bilancio
	 * @param bal la stringa
	 */
	public void setLabel(String bal);
	
	/**
	 * aggiunge il back listener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);
	
	/**
	 * restituisce il rank
	 * @return
	 */
	public int getRank();
}
