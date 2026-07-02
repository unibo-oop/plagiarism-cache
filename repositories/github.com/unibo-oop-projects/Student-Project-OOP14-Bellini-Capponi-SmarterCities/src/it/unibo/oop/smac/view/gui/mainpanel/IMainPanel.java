package it.unibo.oop.smac.view.gui.mainpanel;

import it.unibo.oop.smac.controller.IStreetObserverObserver;
import it.unibo.oop.smac.datatypes.IStreetObserver;

import javax.swing.JPanel;

/**
 * Interfaccia di un panel contenente le informazioni raccolte dagli StreetObservers. Questa
 * interfaccia e' disengata secondo il pattern Observer.
 * 
 * @author Federico Bellini
 */
public interface IMainPanel {

  /**
   * Aggiunge un'{@link IStreetObserver} nella GUI.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} da aggiungere.
   */
  void addStreetObserver(IStreetObserver streetObserver);

  /**
   * Mostra che un'{@link IStreetObserver} ha rilevato dei dati.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} che ha rilevato dei dati.
   */
  void notifySighting(IStreetObserver streetObserver);

  /**
   * Attacca un Observer degli StreetObserver.
   * 
   * @param soo
   *          L'{@link IStreetObserverObserver} da attaccare.
   */
  void attachStreetObserverObserver(IStreetObserverObserver soo);

  /**
   * Restituisce il JPanel.
   * 
   * @return Il JPanel.
   */
  JPanel getPanel();
}
