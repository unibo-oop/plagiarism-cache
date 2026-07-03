package view.Interfaces.Inter;

import java.util.Date;

import javax.swing.JTable;

import controller.Implementations.ShowPrisonersControllerImpl.BackListener;
import controller.Implementations.ShowPrisonersControllerImpl.ComputeListener;

public interface ShowPrisoners {

	/**
	 * crea la tabella in cui mettere i prigionieri
	 * @param table tabella
	 */
	public void createTable(JTable table);

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
	 * aggiunge il compute listener
	 * @param computeListener
	 */
	public void addComputeListener(ComputeListener computeListener);
	
	/**
	 * ritorna la data iniziale della ricerca
	 * @return data iniziale della ricerca
	 */
	public Date getFrom();

	/**
	 * ritorna la data conclusiva della ricerca
	 * @return data conclusiva della ricerca
	 */
	public Date getTo();
}
