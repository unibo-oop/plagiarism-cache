package view.Interfaces.Inter;

import java.util.List;

import controller.Implementations.ViewPrisonerControllerImpl.BackListener;
import controller.Implementations.ViewPrisonerControllerImpl.ViewProfileListener;

public interface ViewPrisoner {


	/**
	 * aggiune il view listener
	 * @param viewListener
	 */
	public void addViewListener(ViewProfileListener viewListener);

	/**
	 * aggiunge il back listener
	 * @param backListener
	 */
	public void addBackListener(BackListener backListener);
	
	/**
	 * ritorna l'id
	 * @return id
	 */
	public int getID();
	
	/**
	 * riempe i campi predefiniti con i valori rispettivi
	 * @param name nome
	 * @param surname cognome
	 * @param birthDate data di nascita
	 * @param start inizio reclusione
	 * @param end fine reclusione
	 */
	public void setProfile(String name, String surname, String birthDate, String start, String end);
	
	/**
	 * mostra un messaggio
	 * @param error il messaggio
	 */
	public void displayErrorMessage(String error);
	
	/**
	 * ritorna il rank
	 * @return
	 */
	public int getRank();

	/**
	 * imposta il testo della lista nella textarea
	 * @param list lista di crimini
	 */
	public void setTextArea(List<String>list);
	
}
