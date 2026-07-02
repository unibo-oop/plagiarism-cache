package it.unibo.infomanager.infomng.view.dialog;

import java.util.Map;

import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;;

/**
 * Interfaccia per i dialoghi Dialog.
 * 
 * @author Alessandro
 *
 */
public interface DialogInterface {

	/**
	 * Metodo per ottenere i dati dai JDialogs.
	 * 
	 * @param o
	 *            Oggetto ObserverInterface
	 * @return Map (String,String) che contiene tutti i dati inseriti nei TextFields del dialogo 
	 * 		   chiamato, se l'operazione non va a buon fine restituisce null
	 * 
	 */
	Map<String, String> getDataString(final ObserverInterface o);
}
