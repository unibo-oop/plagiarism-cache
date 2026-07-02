package it.unibo.oop.smac.view.gui.mainpanel;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Panel che mostra un messaggio quando un nuovo osservatore viene collegato all'applicazione.
 * 
 * @author Federico Bellini
 */
public class MessagePanel extends JPanel {

  private static final long serialVersionUID = -7348946441622348948L;

  /**
   * Logger della classe.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(MessagePanel.class);

  /**
   * Tempo in cui il messaggio visualizzato rimane di differente colore.
   */
  private static final int DELAY = 400;

  /**
   * Area di testo dove scrivere i messaggi di informazioni.
   */
  private final JTextArea message = new JTextArea();

  MessagePanel() {
    super();
    this.setBorder(new TitledBorder(" Info message "));
    this.message.setEditable(false);
    this.message.setBackground(this.getBackground());
    this.add(this.message);
  }

  /**
   * Mostra a video il messaggio passato come argomento. La scritta mostrata rimane di colore rosso
   * per un tempo prefissato.
   * 
   * @param msg
   *          Il messaggio da visualizzare.
   */
  public void showMessage(final String msg) {
    this.message.setText(msg);
    SwingUtilities.invokeLater(() -> this.message.setForeground(Color.RED));
    try {
      Thread.sleep(DELAY);
    } catch (Exception e) {
      LOGGER.error("Thread interrupted while sleeping. ", e);
    }
    SwingUtilities.invokeLater(() -> this.message.setForeground(Color.BLACK));
  }
}
