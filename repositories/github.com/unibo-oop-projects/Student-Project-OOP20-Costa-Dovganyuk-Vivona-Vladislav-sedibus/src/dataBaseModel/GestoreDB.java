package dataBaseModel;

import java.util.List;
import java.util.Map;
import model.piantina.Prenotazione;
import model.utili.Periodo;
import model.piantina.PrenotazioneEstesa;

public interface GestoreDB {

	/**
	 * @param p 
	 * @return the map of reservations in a given period
	 */
	public Map<String, List<Prenotazione>> getMapPrenotazioni(Periodo p);
	
	/**
	 * @param prenotazione
	 * adds a reservation to the file
	 */
	public void addToFile(PrenotazioneEstesa prenotazione);
	
	/**
	 * @param map
	 * @param p upload the map passed to him on the right file (according to the period)
	 */
	void loadMapOnFile(Map<String, List<Prenotazione>> map, Periodo p);
	
}
