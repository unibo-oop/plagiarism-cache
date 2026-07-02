package dungeon.gui;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dungeon.GuiUtilities;


/**
 * The Class MainMenu.
 */
public class MainMenu {

  /** The frame. */
  private JFrame frame;

  /**
   * The main method.
   *
   * @param args the arguments
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static void main(final String[] args) throws IOException {
    new MainMenu();
  }

  /**
   * Instantiates a new main menu.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public MainMenu() throws IOException {
    frame = new JFrame();
    frame.setPreferredSize(new Dimension(
        (int) GuiUtilities.USER_DIMENSION.getWidth() / 2,
        (int) GuiUtilities.USER_DIMENSION.getHeight() / 2));

    JPanel panel = new MainPanel(frame);
    frame.setResizable(true);
    frame.pack();
    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
