package it.unibo.oop.smac.controller;

import it.unibo.oop.smac.datatypes.ISighting;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.view.IView;

import java.util.List;

/**
 * Interfaccia per il controller dell'applicazione. In questo caso la classe che lo implementa deve
 * fungere anche da Observer per gli StreetObserver e per le StolenCars.
 * 
 * @author Federico Bellini
 */
public interface IController {

  /**
   * Notifica che si e' verificato un nuovo passaggio sotto ad un osservatore.
   * 
   * @param streetObserver
   *          L'osservatore che ha compiuto l'avvistamento.
   * @param sighting
   *          Oggetto di tipo {@link ISighting} contenente le informazioni rilevate
   *          dall'osservatore.
   */
  void newSighting(IStreetObserver streetObserver, ISighting sighting);

  /**
   * Aggiunge una View all'applicazione.
   * 
   * @param v
   *          View da aggiungere
   */
  void addView(IView v);

  /**
   * Restituisce le View associate all'applicazione.
   * 
   * @return Le Views attive nell'applicazione
   */
  List<IView> getViews();
}
