package it.unibo.oop.smac.view.stolencars.gui.panel;

import it.unibo.oop.smac.controller.IStolenCarsObserver;
import it.unibo.oop.smac.datatypes.ISighting;

import javax.swing.JPanel;

/**
 * Interfaccia per la gestione di un panel che visualizza le informazioni sulle auto rubate. Questa
 * interfaccia e' disengata secondo il pattern Observer.
 * 
 * @author Francesco Capponi
 */
public interface IStolenCarsPanel {

  /**
   * Attacca l'Observer degli StolenCars.
   * 
   * @param sco
   *          L'{@link IStolenCarsObserver} da attaccare.
   */
  void attachStolenCarsObserver(IStolenCarsObserver sco);

  /**
   * Restituisce il {@link JPanel}.
   * 
   * @return Il {@link JPanel}.
   */
  JPanel getPanel();

  /**
   * Questo metodo deve segnalare che c'e' stato un passaggio di una macchina rubata sotto
   * un'osservatore.
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
