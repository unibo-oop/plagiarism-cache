package it.unibo.oop.smac.datatypes;

import java.util.Optional;

/**
 * Interfaccia che gestisce un oggetto contenente delle informazioni relative ad un singolo
 * osservatore, che possono essere ricavate dai dati presenti nel Model dell'applicazione. I valori
 * restituiti sono tutti degli oggetti della classe Optional.
 * 
 * @author Federico Bellini
 */
public interface IInfoStreetObserver {

  /**
   * Restituisce un Optional che dovrebbe contenere la posizione nello spazio dell'osservatore.
   * 
   * @return La posizione nello spazio dell'osservatore.
   */
  Optional<ICoordinates> getStreetObserverLocation();

  /**
   * Restituisce l'ID dell'osservatore.
   * 
   * @return L'ID dell'osservatore.
   */
  Optional<String> getStreetObserverID();

  /**
   * Restituisce un Optional che dovrebbe contenere il numero di avvistamenti fatti dall'osservatore
   * nell'ultima ora.
   * 
   * @return Il numero di avvistamenti fatti dall'osservatore nell'ultima ora.
   */
  Optional<Integer> getnOfSightLastHour();

  /**
   * Restituisce un Optional che dovrebbe contenere il numero di avvistamenti fatti dall'osservatore
   * oggi (dalle 00:00 AM all'ora corrente).
   * 
   * @return Il numero di avvistamenti fatti dall'osservatore oggi.
   */
  Optional<Integer> getnOfSightToday();

  /**
   * Restituisce un Optional che dovrebbe contenere il numero di avvistamenti fatti dall'osservatore
   * nell'ultima settimana.
   * 
   * @return Il numero di avvistamenti fatti dall'osservatore nell'ultima settimana.
   */
  Optional<Integer> getnOfSightLastWeek();

  /**
   * Restituisce un Optional che dovrebbe contenere il numero di avvistamenti fatti dall'osservatore
   * nell'ultimo mese.
   * 
   * @return Il numero di avvistamenti fatti dall'osservatore nell'ultimo mese.
   */
  Optional<Integer> getnOfSightLastMonth();

  /**
   * Restituisce un Optional che dovrebbe contenere il numero totale di avvistamenti fatti
   * dall'osservatore da quando e' stato avviato.
   * 
   * @return Il numero totale di avvistamenti fatti dall'osservatore da quando e' stato avviato.
   */
  Optional<Integer> getTotalNOfSight();

  /**
   * Restituisce un Optional che dovrebbe contenere la velocita' media registrata nella giornata
   * (dalle 00:00 AM all'ora corrente).
   * 
   * @return La velocita' media registrata nella giornata.
   */
  Optional<Float> getAverageSpeedToday();

  /**
   * Restituisce un Optional che dovrebbe contenere la velocita' media registrata nell'ultima
   * settimana.
   * 
   * @return La velocita' media registrata nell'ultima settimana.
   */
  Optional<Float> getAverageSpeedLastWeek();

  /**
   * Restituisce un Optional che dovrebbe contenere la velocita' media registrata nell'ultimo mese.
   * 
   * @return La velocita' media registrata nell'ultimo mese.
   */
  Optional<Float> getAverageSpeedLastMonth();

  /**
   * Restituisce un Optional che dovrebbe contenere la velocita' massima registrata nella giornata
   * (dalle 00:00 AM all'ora corrente).
   * 
   * @return La velocita' massima registrata nella giornata.
   */
  Optional<Float> getMaxSpeedToday();

  /**
   * Restituisce un Optional che dovrebbe contenere l'ora della giornata in cui si è registrato il
   * numero massimo di passaggi di mezzi (dalle 00:00 AM all'ora corrente).
   * 
   * @return L'ora della giornata in cui si è registrato il numero massimo di passaggi di mezzi.
   */
  Optional<Float> getMaxCarRateToday();

}
