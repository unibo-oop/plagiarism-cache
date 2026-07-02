package it.unibo.oop.smac.view.stolencars.network;

import it.unibo.oop.smac.controller.IStolenCarsObserver;

import java.util.Observable;

/**
 * Classe che estende la classe Observable che permette di notificare determinati jobs dell'arrivo
 * di messaggi da parte di clients.
 * 
 * @author Francesco Capponi
 */
public class Dispatcher extends Observable {

  /**
   * controller che contiene i metodi per la notificazione dell'applicazione dei messaggi ricevuti e
   * interpretati dai vari jobs.
   */
  private final IStolenCarsObserver observer;

  /**
   * Costruttore della classe.
   * 
   * @param obs
   *          dell'applicazione su cui verranno richiamate le funzioni di segnalazione all'arrivo di
   *          eventi dalla rete
   */
  Dispatcher(final IStolenCarsObserver obs) {
    this.observer = obs;
  }

  /**
   * Restituisce l'observer dell'applicazione tramite cui poter comunicare i dati al controller.
   * 
   * @return observer observer dell'applicazione tramite cui poter comunicare i dati al controller
   */
  public IStolenCarsObserver getStolenCarsObserver() {
    return observer;
  }

  /**
   * Notifica gli observer.
   */
  @Override
  public void notifyObservers(final Object arg) {
    // Notifica che lo stato dell'applicazione è cambiato
    setChanged();
    // e quindi al richiamo della funzione notifyObserves, devono essere
    // richiamati gli observers
    super.notifyObservers(arg);
  }

}
