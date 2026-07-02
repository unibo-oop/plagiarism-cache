package it.unibo.oop.smac.database.model;

/**
 * Eccezione lanciata quando non vengono trovati i dati richiesti nel database.
 * 
 * @author Francesco Capponi
 */
public class DatabaseNotFoundException extends Exception {

  private static final long serialVersionUID = 5160048105729180736L;

  /**
   * Costruttore dell'eccezione.
   * 
   * @param text
   *          Stringa con messaggio da visualizzare.
   */
  public DatabaseNotFoundException(final String text) {
    super(text);
  }

}
