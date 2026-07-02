package it.unibo.oop.smac.datatypes;

/**
 * Interfaccia per una classe che implementa le coordinate di un punto dello spazio.
 * 
 * @author Federico Bellini
 */
public interface ICoordinates {

  /**
   * Restituisce la latitudine del punto.
   * 
   * @return La latitudine del punto.
   */
  Float getLatitude();

  /**
   * Restituisce la longitudine del punto.
   * 
   * @return La longitudine del punto.
   */
  Float getLongitude();

}
