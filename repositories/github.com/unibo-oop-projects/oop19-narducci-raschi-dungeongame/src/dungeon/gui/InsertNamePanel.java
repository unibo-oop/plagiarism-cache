package dungeon.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dungeon.GuiUtilities;

/**
 * The Class InsertNamePanel.
 */
public final class InsertNamePanel extends JPanel {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -2921653903846697866L;

  /** The img. */
  private final BufferedImage img;

  /**
   * Instantiates a new insert name panel.
   *
   * @param frame the frame
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public InsertNamePanel(final JFrame frame) throws IOException {
    this.setPreferredSize(new Dimension(
        (int) GuiUtilities.USER_DIMENSION.getWidth() / 2,
        (int) GuiUtilities.USER_DIMENSION.getHeight() / 2));
    img = ImageIO.read(ClassLoader.getSystemResource("menu.jpg"));
    JButton button = new JButton("Enter");
    JTextField textField = new JTextField(16);
    textField.setText("Enter Name");
    this.add(button);
    button.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent e) {
        String text = textField.getText();
        if (!text.isEmpty()) {
          frame.getContentPane().removeAll();
          frame.dispose();
          new GameWorld(text);
          frame.repaint();
        }
      }
    });
    this.add(textField);
    this.setVisible(true);
    frame.getContentPane().add(this);
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void paintComponent(final Graphics graphics) {
    super.paintComponent(graphics);
    graphics.drawImage(img, this.getX(), this.getY(),
        this.getWidth(), this.getHeight(), null);
  }
}
