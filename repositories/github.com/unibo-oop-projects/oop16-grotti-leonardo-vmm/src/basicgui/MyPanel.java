package basicgui;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements MyPanelInterface {

  private static final long serialVersionUID = -2306819753458512615L;

  /**
   * Costruttore del pannello.
   * @param lm è il layout del panello.
   */
  public MyPanel(final LayoutManager lm) {
    super(lm);
    setVisible(true);
    this.setBackground(Color.GRAY);
  }
}
