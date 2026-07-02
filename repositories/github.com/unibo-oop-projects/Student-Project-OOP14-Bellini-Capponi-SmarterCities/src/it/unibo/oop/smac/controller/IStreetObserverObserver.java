package it.unibo.oop.smac.controller;

import it.unibo.oop.smac.datatypes.IInfoStreetObserver;
import it.unibo.oop.smac.datatypes.IStreetObserver;

/**
 * Questa interfaccia funzionale ha il compito di astrarre la logica del sistema dalla parte di
 * View, disaccoppiando la richiesta della View di ricevere delle informazioni su di uno
 * {@link IStreetObserver} dall'effettiva implementazione del Model.
 * 
 * @author Federico Bellini
 */

@FunctionalInterface
public interface IStreetObserverObserver {

  /**
   * Restituisce un oggetto del tipo {@link IInfoStreetObserver} contenente le informazioni sull'
   * {@link IStreetObserver} richiesto.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} di cui si richiedono informazioni.
   * @return Un oggetto {@link IStreetObserver} con le informazioni richieste.
   */
  IInfoStreetObserver getStreetObserverInfo(IStreetObserver streetObserver);
}
