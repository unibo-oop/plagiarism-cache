package it.unibo.oop.smac.view.gui.locationpanel;

import it.unibo.oop.smac.datatypes.IStreetObserver;

import javax.swing.JPanel;

/**
 * Interfaccia del LocationPanel. La classe che implementa questa interfaccia ha il compito di
 * mostrare su di una mappa le posizioni degli {@link IStreetObserver}.
 * 
 * @author Federico Bellini
 */
public interface ILocationPanel {

  /**
   * Aggiunge un nuovo {@link IStreetObserver} alla mappa.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} da aggiungere.
   */
  void addStreetObserver(IStreetObserver streetObserver);

  /**
   * Interagisce con la mappa mostrando in qualche modo un avvenuto passaggio sotto di un
   * {@link IStreetObserver}.
   * 
   * @param streetObserver
   *          L'{@link IStreetObserver} che ha rilevato un passaggio.
   */
  void notifySighting(IStreetObserver streetObserver);

  /**
   * Restituisce il JPanel.
   * 
   * @return Il JPanel.
   */
  JPanel getPanel();
}
