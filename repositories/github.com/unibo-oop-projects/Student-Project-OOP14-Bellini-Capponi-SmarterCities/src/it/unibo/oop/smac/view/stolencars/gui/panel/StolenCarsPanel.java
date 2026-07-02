package it.unibo.oop.smac.view.stolencars.gui.panel;

import it.unibo.oop.smac.controller.IStolenCarsObserver;
import it.unibo.oop.smac.datatypes.ISighting;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Implementazione del JPanel contenente le informazioni principali riguardanti le stolen cars.
 * Questa classe e' implementata utilizzando il pattern Observer.
 * 
 * @author Francesco Capponi
 */
public class StolenCarsPanel extends JPanel implements IStolenCarsPanel {

  private static final long serialVersionUID = -3442595458503908271L;

  /**
   * Primo pannello che gestisce l'inserimento delle auto rubate nel database.
   */
  private final InsertionPanel insertionPanel = new InsertionPanel();

  /**
   * Pannello che gestisce una tabella che mostra la lista di auto rubate.
   */
  private final TablePanel tablePanel = new TablePanel();

  /**
   * Pannello che mostra i dati sulle auto rubate quando esse vengono avvistate.
   */
  private final StolenCarSightPanel lastStolenSeenPanel = new StolenCarSightPanel();

  /**
   * Pannello che mostra i dati dell'ultima auto avvistata dagli osservatori.
   */
  private final LastCarSeenPanel lastCarSeenPanel = new LastCarSeenPanel();

  /**
   * Costruttore pubblico della classe.
   */
  public StolenCarsPanel() {
    super();
    this.setLayout(new BorderLayout());

    final JPanel innerPanel = new JPanel(new BorderLayout());
    innerPanel.add(this.insertionPanel, BorderLayout.NORTH);
    innerPanel.add(this.lastStolenSeenPanel, BorderLayout.CENTER);

    this.add(this.tablePanel, BorderLayout.CENTER);
    this.add(innerPanel, BorderLayout.WEST);
    this.add(lastCarSeenPanel, BorderLayout.SOUTH);
  }

  /**
   * Attacca l'Observer degli StolenCars.
   * 
   * @param stolenCarsObserver
   *          L'{@link IStolenCarsObserver} da attaccare.
   */
  @Override
  public void attachStolenCarsObserver(final IStolenCarsObserver stolenCarsObserver) {
    this.tablePanel.attachStolenCarsObserver(stolenCarsObserver);
    this.insertionPanel.attachStolenCarsObserver(stolenCarsObserver);
  }

  /**
   * Restituisce il JPanel.
   * 
   * @return Il JPanel.
   */
  @Override
  public JPanel getPanel() {
    return this;
  }

  /**
   * Questo metodo deve segnalare che c'e' stato un passaggio sotto un'osservatore di una macchina
   * rubata.
   * 
   * @param sighting
   *          L'{@link ISighting} dell'avvistamento della macchina rubata.
   */
  @Override
  public void newSightingStolenCar(final ISighting sighting) {
    lastStolenSeenPanel.newSightingStolenCar(sighting);
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
    final String message = "The plate of the last car spotted is " + sighting.getLicensePlate()
        + " at " + sighting.getDate();
    lastCarSeenPanel.showMessage(message);
  }
}
