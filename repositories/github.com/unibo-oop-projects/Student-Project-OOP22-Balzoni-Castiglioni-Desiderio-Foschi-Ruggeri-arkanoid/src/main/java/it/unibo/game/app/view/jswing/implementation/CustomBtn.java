package it.unibo.game.app.view.jswing.implementation;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

/**
 * Class that creates custom buttons.
 */
public class CustomBtn extends JButton {
  private Font btnFont;

  /**
   * Constructor of this class.
   * 
   * @param size size of the button.
   * @param text text of the button.
   */
  public CustomBtn(final int size, final String text) {
    setText(text);
    btnFont = new Font("arial", Font.BOLD, size);
    setFont(btnFont);
    setBackground(Color.WHITE);
  }
}
