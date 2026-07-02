package View;

import java.io.File;

import Control.Control;

/**
 * Classe View principale del programma
 * in cui vengono visualizzati tutti i bottoni per le varie operazioni
 *
 * @author Filippo Solazzi
 *
 */
public interface Merc_View {
	
	//Metodo utilizzato per impostare il control
	/**
	* Inizializzazzione del control e del Listener
	*
	* @param ctr
	*          Passaggio del Control
	*/
	void set_Control(Control ctr);
	
	/**
	* Metodo utilizzato per impostare la JLabel 
	* una volta selezionata la Repository
	* e per abilitare i pulsanti per effettuare le operazioni
	*
	* @param repo
	*          Directory della repository
	*/
	void set_Repo(File repo);
	
	/**
	* Metodo utilizzato per impostare la JLabel 
	* se si effettua il Clone da una Repository
	*
	* @param url
	*          Stringa dell'URL
	*/
	void set_Clone(String url);
}
