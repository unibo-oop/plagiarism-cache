package it.unibo.oop.smac.datatypes;

import it.unibo.oop.smac.database.model.StreetObserverNotValidException;

import java.util.Date;

/**
 * Interfaccia per un'avvistamento di un osservatore. La classe che la implementa deve raccogliere
 * tutte le informazioni generate dall'osservatore, quali la data dell' avvistamento, la targa
 * dell'auto avvistata e la sua velocita'.
 * 
 * @author Federico Bellini
 */
public interface ISighting {

  /**
   * Restituisce l'{@link IStreetObserver} che ha generato le informazioni di questo avvistamento.
   * 
   * @return L'{@link IStreetObserver} autore dell'avvistamento.
   * @throws StreetObserverNotValidException
   *           restituita se lo streetObserver associato non è valido
   */
  IStreetObserver getStreetObserver() throws StreetObserverNotValidException;

  /**
   * Restituisce la {@link Date} relativa al momento dell'avvistamento.
   * 
   * @return la {@link Date} relativa al momento dell'avvistamento.
   */
  Date getDate();

  /**
   * Restituisce la {@link LicensePlate} dell'auto avvistata.
   * 
   * @return La {@link LicensePlate} dell'auto avvistata.
   */
  LicensePlate getLicensePlate();

  /**
   * Restituisce la velocita' dell'auto avvistata.
   * 
   * @return La velocita' dell'auto avvistata.
   */
  Float getSpeed();

}