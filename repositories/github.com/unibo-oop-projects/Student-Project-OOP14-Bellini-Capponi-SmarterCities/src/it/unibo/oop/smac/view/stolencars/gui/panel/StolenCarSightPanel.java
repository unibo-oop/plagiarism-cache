package it.unibo.oop.smac.view.stolencars.gui.panel;

import it.unibo.oop.smac.datatypes.ISighting;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

/**
 * Classe che implementa un pannello che mostra i dati sulle auto rubate quando esse vengono
 * avvistate.
 * 
 * @author Francesco Capponi
 */
public class StolenCarSightPanel extends JPanel {

  private static final long serialVersionUID = -7140640507027357573L;

  /**
   * Tabella contenente le informazioni sugli avvistamenti delle auto rubate.
   */
  private final StolenCarSightingTable stolenCarsSightingTable;

  /**
   * Costruttore pubblico della classe.
   */
  public StolenCarSightPanel() {
    super();
    this.setLayout(new FlowLayout());
    this.setBorder(new TitledBorder("Stolen cars sighting table"));

    // creo la tabella con i dati
    this.stolenCarsSightingTable = new StolenCarSightingTable();
    this.add(new JScrollPane(new JTable(stolenCarsSightingTable)));
  }

  /**
   * Questo metodo deve segnalare che c'e' stato un passaggio sotto un'osservatore di una macchina
   * rubata.
   * 
   * @param sighting
   *          L'{@link ISighting} dell'avvistamento della macchina rubata.
   */
  public void newSightingStolenCar(final ISighting sighting) {
    stolenCarsSightingTable.insertSighting(sighting);
  }

}
