package it.unibo.infomanager.infomng.controller;

import java.util.Collection;

public interface Navigator<T> {
	
	static Navigator<?> creatNavigator(Collection<?> elementi){
		return new ListOfObjectImpl<>(elementi);
	}
	
	/**
	 * Ottiene il prossimo elemento
	 * @return
	 * Ritorna il prossimo elemento, se l'elemento attuale è l'ultimo ritorna l'ultimo
	 */
	T avanti();

	/**
	 * Ottiene l'elemento precedente
	 * @return
	 * l'elemento precedente, se l'elemento attuale è il primo ritorna il primo
	 */
	T indietro();

	/**
	 * ritorna se ci si trova alla posizione numero 1
	 * @return
	 * true se è la prima posizione
	 */
	Boolean isPrimo();

	/**
	 * ritorna se ci si trova all'ultima posizione
	 * @return
	 * true se è l'ultima posizione
	 */
	Boolean isUltimo();

}