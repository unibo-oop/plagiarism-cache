package it.unibo.oop.smac.view.stolencars.gui.panel;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pannello che mostra le informazioni sull'ultimo avvistamento prodotto dagli osservatori.
 * 
 * @author Francesco Capponi
 */
public class LastCarSeenPanel extends JPanel {

  private static final long serialVersionUID = 3065354652371642298L;

  /**
   * Logger della classe.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(LastCarSeenPanel.class);

  /**
   * Tempo in cui il messaggio visualizzato rimane di differente colore.
   */
  private static final int DELAY = 400;

  /**
   * Area di testo dove scrivere i messaggi di informazioni.
   */
  private final JTextArea message = new JTextArea();

  LastCarSeenPanel() {
    super();
    this.setBorder(new TitledBorder(" Last car seen "));
    this.message.setEditable(false);
    this.message.setBackground(this.getBackground());
    this.add(this.message);
  }

  /**
   * Mostra a video i dati dell'ultimo avvistamento compiuto.
   * 
   * @param sightingMessage
   *          L'avvistamento da mostrare.
   */
  public void showMessage(final String sightingMessage) {
    this.message.setText(sightingMessage);
    SwingUtilities.invokeLater(() -> this.message.setForeground(Color.RED));
    try {
      Thread.sleep(DELAY);
    } catch (Exception e) {
      LOGGER.error("Thread interrupted while sleeping. ", e);
    }
    SwingUtilities.invokeLater(() -> this.message.setForeground(Color.BLACK));
  }

}
