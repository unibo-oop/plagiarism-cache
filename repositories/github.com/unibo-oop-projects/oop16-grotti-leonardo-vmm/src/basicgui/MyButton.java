package basicgui;

import java.awt.Color;

import javax.swing.JButton;

public class MyButton extends JButton implements MyButtonInterface {

  private static final long serialVersionUID = -4738512682435416771L;

  /**
   * Costruttore del metodo MyButton.
   * @param title è il testo rappresentato nel bottone.
   */
  public MyButton(final String title) {
    super(title);
    this.setBackground(Color.DARK_GRAY);
    this.setForeground(Color.WHITE);
  }

}
