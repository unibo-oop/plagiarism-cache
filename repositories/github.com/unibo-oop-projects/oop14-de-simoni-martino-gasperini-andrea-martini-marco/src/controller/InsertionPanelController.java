package controller;


import java.util.HashSet;

import javax.swing.JPanel;

/**
 * 
 *
 * Astratta utile per i pannelli che hanno elementi da inserire e/o rimuovere. Utile solo un po'.
 *
 * @param <X> Oggetto da inserire
 * @param <Y> Tipo di pannello associato
 * 
 * @author Martino De Simoni
 */

/*
 * Anche se è il controller di un pannello a inserimento, non esiste la classe InsertionPanel.
 * L'inserimento può avvenire in un migliaio di modi (JList, Listener..) e non ha senso creare astratte o interfacce a parte.
 */

public abstract class InsertionPanelController <X, Y extends JPanel> extends PanelController<Y>  {
	
	/**
	 * Insieme di oggetti inseriti.
	 */
	
	protected HashSet<X> set;
	
	/**
	 * Toglie un elemento. Consigliato l'override.
	 * @param delendum Elemento da rimuovere
	 */
	
	protected void delete(X delendum) {
		
		set.remove(delendum);
	
	}

	/**
	 * Aggiunge un elemento. Consigliato l'override.
	 * @param inserendum Elemento da rimuovere
	 */
	
	public void insert(X inserendum) {
		
		set.add(inserendum);
		
	}
	
}
