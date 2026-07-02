package model.Interfaces;

import java.util.Date;


/**
 * Interfaccia di un viaggio nave
 * @author Helena Zaccarelli
 */

public interface Viaggio extends Nave{
	
	/**
	 * Metodo che ritorna il codice univoco del viaggio
	 * @return
	 */
	public int getId();
	
	/**
	 * Metodo che setta il codice univoco del viaggio
	 * @param id
	 */
	public void setId(int id);
	
	/**
	 * Metodo che ritorna il porto di provenienza della nave oggetto del viaggio
	 * @param provenienza
	 */
	public String getProvenienza();
	
	/**
	 * Metodo che ritorna il porto di destinazione della nave oggetto del viaggio
	 * @return
	 */
	public String getDestinazione();
	
	/**
	 * Metodo che ritorna il peso della merce in sbarco (espresso in tonnellate)
	 * @return
	 */
	public int getSbarco();
	
	/**
	 * Metodo che ritorna il peso della merce in transito (espresso in tonnellate)
	 * @return
	 */
	public int getTransito();

	/**
	 * Metodo che ritorna gli spazi occupati dalla merce in transito
	 * @return
	 */
	public int getSpaziTransito();
	
	/**
	 * Metodo che ritorna la data di passaggio in porto della nave
	 * @return
	 */
	public Date getData();
	
	/**
	 * Metodo che ritorna il peso caricabile disponibile (espresso in tonnellate)
	 * @return
	 */
	public int getCarico();
	
	/**
	 * Metodo che setta il peso caricabile disponibile (espresso in tonnellate)
	 * @param carico
	 */
	public void setCarico(int carico);
	
	/**
	 * Metodo che ritorna gli spazi disponibili per il carico
	 * @return
	 */
	public int getSpaziCarico();
	
	/**
	 * Metodo che setta gli spazi disponibili per il carico
	 * @param spaziCarico
	 */
	public void setSpaziCarico(int spaziCarico);
	
	/**
	 * Metodo che ritorna la durata del viaggio (espresso in giorni)
	 * @return
	 */
	public int getDurata();
	
	/**
	 * Metodo che ritorna la disponibilità  o meno della nave a caricare durante la sosta in porto
	 * @return
	 */
	public boolean getOpzione();
	
	/**
	 * Metodo che ritorna lo stato del viaggio e conseguentemente la disponibilità o meno del viaggio per eventuali prenotazioni 
	 * Il viaggio smetterà  di essere disponibile per nuove prenotazioni a nave ripartita
	 * @return
	 */
	public boolean getPartenza();
	
	/**
	 * Metodo che setta lo stato del viaggio e conseguentemente la disponibilità o meno del viaggio per eventuali prenotazioni 
	 * Il viaggio smetterà di essere disponibile per nuove prenotazioni a nave ripartita
	 * @param partenza
	 */
	public void setPartenza(boolean partenza);
}
