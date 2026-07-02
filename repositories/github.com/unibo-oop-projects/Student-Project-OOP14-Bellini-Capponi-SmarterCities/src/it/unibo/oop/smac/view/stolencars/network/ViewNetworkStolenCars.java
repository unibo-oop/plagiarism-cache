package it.unibo.oop.smac.view.stolencars.network;

import it.unibo.oop.smac.controller.IStolenCarsObserver;
import it.unibo.oop.smac.controller.IStreetObserverObserver;
import it.unibo.oop.smac.datatypes.ISighting;
import it.unibo.oop.smac.datatypes.IStreetObserver;
import it.unibo.oop.smac.view.stolencars.IViewStolenCars;

/**
 * Implementazione dell'interfaccia IView con utilizzo di GUI. Questa classe e' implementata secondo
 * il pattern Observer(metodi attachObservers).
 * 
 * @author Francesco Capponi
 */
public class ViewNetworkStolenCars implements IViewStolenCars {

  /**
   * Observer dei messaggi generati della View
   */
  private IStolenCarsObserver stolenCarsObserver;

  /**
   * controlla che l'appliazione sia stata inizializzata solo una volta
   */
  private Boolean initialized = false;

  /**
   * Inizializza il Network Server
   */
  private void initialize() {
    if (this.initialized && this.stolenCarsObserver != null) {
      new NetServer(this.stolenCarsObserver).run();
    }
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
    if (this.initialized) {
      throw new IllegalAccessError("You can't initialize more than one observer!");
    }
    this.initialized = true;
    this.stolenCarsObserver = sco;
    initialize();
  }

  /**
   * La seguente funzione, nell'implementazione corrente del Network server, non ha alcun effetto.
   */
  @Override
  public void newSightingStolenCar(final ISighting iSighting) {
    // No implementation necessary
  }

  /**
   * La seguente funzione, nell'implementazione corrente del Network server, non ha alcun effetto.
   */
  @Override
  public void addStreetObserver(final IStreetObserver streetObserver) {
    // No implementation necessary
  }

  /**
   * La seguente funzione, nell'implementazione corrente del Network server, non ha alcun effetto.
   */
  @Override
  public void newSighting(final IStreetObserver streetObserver) {
    // No implementation necessary
  }

  /**
   * La seguente funzione, nell'implementazione corrente del Network server, non ha alcun effetto.
   */
  @Override
  public void attachStreetObserverObserver(final IStreetObserverObserver soo) {
    // No implementation necessary
  }

  /**
   * La seguente funzione, nell'implementazione corrente del Network server, non ha alcun effetto.
   */
  @Override
  public void showLastSighting(final ISighting sighting) {
    // No implementation necessary
  }

}
