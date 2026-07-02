package it.unibo.oop.smac.view.stolencars;

import it.unibo.oop.smac.controller.IStolenCarsObserver;
import it.unibo.oop.smac.datatypes.ISighting;
import it.unibo.oop.smac.view.IView;

/**
 * Interfaccia realizzata per la gestione della parte di View del programma. Essa puo' essere
 * implementata sia da un'interfaccia di tipo grafico sia non. In questo modo viene garantita la
 * massima genericità ed indipendenza della parte di View dell'applicazione dalle parti di Model e
 * Controller.
 * 
 * @author Francesco Capponi
 */
public interface IViewStolenCars extends IView {

  /**
   * Questo metodo deve attaccare l'Observer passato come parametro alle StolenCars presenti nella
   * View. In questo modo e' l'Observer preso come paramentro che gestisce il comportamento
   * dell'applicazione quando vengono fatte delle richieste su delle StolenCars da parte della View.
   * 
   * @param sco
   *          L'{@link IStolenCarsObserver} da attaccare alle StolenCars presenti nella View.
   */
  void attachStolenCarsObserver(IStolenCarsObserver sco);

  /**
   * Questo metodo deve segnalare che c'e' stato un passaggio sotto un'osservatore di una macchina
   * rubata.
   * 
   * @param sighting
   *          L'{@link ISighting} dell'avvistamento della macchina rubata.
   */
  void newSightingStolenCar(ISighting sighting);

  /**
   * Questo metodo visualizza i dati sull'ultimo avvistamento compiuto in generale dagli
   * osservatori.
   * 
   * @param sighting
   *          L'{@link ISighting} dell'ultimo avvistamento compiuto.
   */
  void showLastSighting(ISighting sighting);
}
