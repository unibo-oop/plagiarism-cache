package it.unibo.oop.smac.database.model;

/**
 * Eccezione lanciata quando viene passata ai metodi del database uno StreetObserver non valido.
 * 
 * @author Francesco Capponi
 */
public class StreetObserverNotValidException extends Exception {

  private static final long serialVersionUID = -6862511711498784049L;

  /**
   * Costruttore dell'eccezione.
   */
  public StreetObserverNotValidException() {
    super("Lo Street Observer passato non è valido");
  }

}
