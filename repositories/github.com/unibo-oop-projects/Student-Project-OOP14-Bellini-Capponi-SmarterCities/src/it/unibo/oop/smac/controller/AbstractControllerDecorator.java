/**
 * 
 */
package it.unibo.oop.smac.controller;

import it.unibo.oop.smac.datatypes.ISighting;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.view.IView;

import java.util.List;

/**
 * Classe astratta utilizzata per decorare il controller di base dell'applicazione.
 * 
 * @author Francesco Capponi
 */
abstract class AbstractControllerDecorator implements IController {

  private final IController controllerDecorated;

  /**
   * Costruttore del decorator
   */
  public AbstractControllerDecorator(final IController cDecorated) {
    this.controllerDecorated = cDecorated;
  }

  /**
   * Notifica che si e' verificato un nuovo passaggio sotto ad un osservatore.
   * 
   * @param streetObserver
   *          L'osservatore che ha compiuto l'avvistamento.
   * @param sighting
   *          Oggetto di tipo {@link ISighting} contenente le informazioni rilevate
   *          dall'osservatore.
   */
  @Override
  public void newSighting(final IStreetObserver streetObserver, final ISighting sighting) {
    controllerDecorated.newSighting(streetObserver, sighting);

  }

  /**
   * Viene aggiunta una View all'applicazione
   * 
   * @param v
   *          view da aggiungere
   */
  @Override
  public void addView(final IView v) {
    controllerDecorated.addView(v);
  }

  /**
   * Restituisce le view associate all'applicazione
   * 
   * @return views attive nell'applicazione
   */
  @Override
  public List<IView> getViews() {
    return controllerDecorated.getViews();
  }
}
