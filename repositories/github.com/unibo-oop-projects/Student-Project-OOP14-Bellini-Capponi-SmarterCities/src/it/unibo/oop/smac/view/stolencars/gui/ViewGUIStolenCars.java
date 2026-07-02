package it.unibo.oop.smac.view.stolencars.gui;

import it.unibo.oop.smac.controller.IStolenCarsObserver;
import it.unibo.oop.smac.datatypes.ISighting;
import it.unibo.oop.smac.view.gui.ViewGUI;
import it.unibo.oop.smac.view.stolencars.IViewStolenCars;
import it.unibo.oop.smac.view.stolencars.gui.panel.IStolenCarsPanel;
import it.unibo.oop.smac.view.stolencars.gui.panel.StolenCarsPanel;

/**
 * Implementazione dell'interfaccia {@link IViewStolenCars} con utilizzo di GUI. Questa classe e'
 * implementata secondo il pattern Observer(metodi attachObservers).
 * 
 * @author Francesco Capponi
 */
public class ViewGUIStolenCars extends ViewGUI implements IViewStolenCars {

  private static final long serialVersionUID = -2827458092444569608L;
  /**
   * Pannello contenente le informazioni sulle auto rubate.
   */
  private final IStolenCarsPanel stolenCarsPanel = new StolenCarsPanel();

  /**
   * Costruttore pubblico della GUI.
   */
  public ViewGUIStolenCars() {
    super();
    super.addTab(" Stolen Cars ", stolenCarsPanel.getPanel());
  }

  /**
   * Questo metodo attacca l'Observer passato come parametro alle StolenCars presenti. In questo
   * modo e' l'Observer preso come paramentro che gestisce il comportamento dell'applicazione quando
   * vengono fatte delle richieste su delle StolenCars da parte della View.
   * 
   * @param sco
   *          L'{@link IStolenCarsObserver} da attaccare alle StolenCars presenti nella View.
   */
  @Override
  public void attachStolenCarsObserver(final IStolenCarsObserver sco) {
    this.stolenCarsPanel.attachStolenCarsObserver(sco);
  }

  /**
   * Questo metodo deve segnalare che c'e' stato un passaggio sotto un'osservatore di una macchina
   * rubata.
   * 
   * @param iSighting
   *          L'{@link ISighting} dell'avvistamento della macchina rubata.
   */
  @Override
  public void newSightingStolenCar(final ISighting iSighting) {
    this.stolenCarsPanel.newSightingStolenCar(iSighting);
  }

  /**
   * Questo metodo visualizza i dati sull'ultimo avvistamento compiuto in generale dagli
   * osservatori.
   * 
   * @param sighting
   *          L'{@link ISighting} dell'ultimo avvistamento compiuto.
   */
  @Override
  public void showLastSighting(final ISighting sighting) {
    this.stolenCarsPanel.showLastSighting(sighting);
  };

}
