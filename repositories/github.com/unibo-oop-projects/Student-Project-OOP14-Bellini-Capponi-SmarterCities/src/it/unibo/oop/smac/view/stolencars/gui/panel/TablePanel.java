package it.unibo.oop.smac.view.stolencars.gui.panel;

import it.unibo.oop.smac.controller.IStolenCarsObserver;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

/**
 * Classe che implementa un pannello che mostra una tabella contenente la lista di tutte le macchine
 * rubate conosciute. Questa classe e' implementata secondo il pattern Observer.
 * 
 * @author Francesco Capponi
 */
public class TablePanel extends JPanel {

  private static final long serialVersionUID = -7140640507027357573L;

  /**
   * Arco di tempo che intercorre tra ogni aggiornamento della tabella contenente le auto rubate.
   */
  private static final int DELAY = 1000;

  /**
   * Profondita' della finestra.
   */
  private static final int WIDTH = 29;

  /**
   * Altezza della finestra.
   */
  private static final int HEIGHT = 63;

  /**
   * Observer delle auto rubate.
   */
  private IStolenCarsObserver stolenCarsObserver;

  /**
   * Costruttore pubblico della classe.
   */
  public TablePanel() {
    super();
    this.setBorder(new TitledBorder("Stolen Cars Database"));
    this.setLayout(new FlowLayout());

    this.add(new JLabel("Informations on Stolen Cars"));

    // creo la tabella con i dati
    final StolenCarTable stolenCarTable = new StolenCarTable();
    final JTable table = new JTable(stolenCarTable);
    table.setPreferredSize(new Dimension((Toolkit.getDefaultToolkit().getScreenSize().width / 100)
        * WIDTH, (Toolkit.getDefaultToolkit().getScreenSize().height / 100) * HEIGHT));
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    final JScrollPane jScrollPane = new JScrollPane(table);
    jScrollPane.setViewportView(table);
    this.add(jScrollPane);

    // imposto un timer che aggiorna i dati nella tabella ad intervalli di tempo stabiliti
    final Timer timer = new Timer(DELAY, (e) -> SwingUtilities.invokeLater(() -> {
      stolenCarTable.updateList(stolenCarsObserver.getStolenCarsInfoList());
      this.revalidate();
      this.repaint();
    }));
    timer.setRepeats(true);
    timer.start();
  }

  /**
   * Attacca l'Observer degli StolenCars.
   * 
   * @param sco
   *          L'{@link IStolenCarsObserver} da attaccare.
   */
  public void attachStolenCarsObserver(final IStolenCarsObserver sco) {
    this.stolenCarsObserver = sco;
  }

}
