package it.unibo.infomanager.infomng.controller;

import java.sql.Date;
import java.util.Optional;

/***
 * oggetto che incapsula un record di una tabella
 * contiene di default id della riga, ultima modifica e creazione
 * @author mattiaberretti
 *
 */
public interface TableRow {

	/***
	 * crea un nuovo record in una tabella
	 * @param nomeTabella
	 * nome della tabella che conterra il record
	 * @return
	 * un oggetto reppresentativo del nuovo record
	 */
	static TableRow oggettoDaTabella(String nomeTabella){
		return new Oggetto(nomeTabella);
	}
	
	/***
	 * id dell'oggetto
	 * N.B.
	 * 	è impostato a null nel caso non sia ancora stato salvato
	 * @return
	 */
	Integer objectId();

	Optional<Date> creazione();

	Optional<Date> modifica();

	/**
	 * ricava il valore di una colonna
	 * @param key
	 * @return
	 */
	Object getObject(String key);

	/**
	 * imposta un valore all'interno di una colonna
	 * @param key
	 * nome della colonna
	 * @param value
	 * valore da inserire
	 */
	void setObjectValue(String key, Object value);

	/***
	 * salva il record
	 * @return
	 * il risultato del salvataggio
	 * @throws IllegalArgumentException
	 * si verifica nel caso che il tipo di dato di una colonna non sia riconoscuto
	 * 
	 * Tipi conoscuti
	 * 	•)String
	 *  •)Integer
	 *  •)Double
	 *  •)Float
	 *  •)Date (il formato di sql)
	 */
	boolean salva() throws IllegalArgumentException;

	/**
	 * elimina il record dalla tabella
	 * @return
	 * il risultato dell'eliminazione
	 */
	boolean elimina();

}