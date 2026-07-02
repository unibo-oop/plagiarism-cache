package it.unibo.oop.smac.datatypes;

/**
 * Interfaccia per gestire le funzioni di un'osservatore.
 * 
 * @author Federico Bellini
 */
public interface IStreetObserver {

  /**
   * Restituisce le coordinate dove è posizionato l'osservatore.
   * 
   * @return Le {@link ICoordinates} relative alla posizione dell'osservatore.
   */
  ICoordinates getCoordinates();

  /**
   * Restituisce la latitudine dell'osservatore.
   * 
   * @return La latitudine dell'osservatore.
   */
  Float getLatitude();

  /**
   * Restituisce la longitudine dell'osservatore.
   * 
   * @return La longitudine dell'osservatore.
   */
  Float getLongitude();

  /**
   * Restituisce una stringa contenente un identificatore univoco per l'osservatore.
   * 
   * @return L'identificatore univoco dell'osservatore.
   */
  String getId();

}