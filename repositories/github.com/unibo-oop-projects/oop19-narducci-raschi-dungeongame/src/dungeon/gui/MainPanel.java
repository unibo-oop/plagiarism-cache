package dungeon.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dungeon.GuiUtilities;

/**
 * The Class MainPanel.
 */
public final class MainPanel extends JPanel {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 4507083119112744315L;

  /** The Constant WIDTH_DIVISOR. */
  private static final int WIDTH_DIVISOR = 3;

  /** The Constant HEIGHT_DIVISOR. */
  private static final int HEIGHT_DIVISOR = 5;

  /** The Constant FONT_SIZE. */
  private static final int FONT_SIZE = 20;

  /** The selected rect. */
  private Optional<Rectangle> selectedRect = Optional.empty();

  /** The new game. */
  private Rectangle newGame;

  /** The exit game. */
  private Rectangle exitGame;

  /** The img. */
  private BufferedImage img;

  /**
   * Instantiates a new main panel.
   *
   * @param frame the frame
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public MainPanel(final JFrame frame) throws IOException {
    this.setPreferredSize(new Dimension(
        (int) GuiUtilities.USER_DIMENSION.getWidth() / 2,
        (int) GuiUtilities.USER_DIMENSION.getHeight() / 2));
    img = ImageIO.read(ClassLoader.getSystemResource("menu.jpg"));
    newGame = new Rectangle(
        (int) this.getPreferredSize().getWidth() / WIDTH_DIVISOR,
        (int) this.getPreferredSize().getHeight() / HEIGHT_DIVISOR,
        (int) this.getPreferredSize().getWidth() / WIDTH_DIVISOR,
        (int) this.getPreferredSize().getHeight() / HEIGHT_DIVISOR);
    exitGame = new Rectangle(
        (int) this.getPreferredSize().getWidth() / WIDTH_DIVISOR,
        (int) this.getPreferredSize().getHeight() / HEIGHT_DIVISOR * 3,
        (int) this.getPreferredSize().getWidth() / WIDTH_DIVISOR,
        (int) this.getPreferredSize().getHeight() / HEIGHT_DIVISOR);
    final MouseAdapter mouse = new MouseAdapter() {

      @Override
      public void mouseMoved(final MouseEvent e) {
        if (newGame.contains(e.getPoint())) {
          selectedRect = Optional.of(newGame);
        } else if (exitGame.contains(e.getPoint())) {
          selectedRect = Optional.of(exitGame);
        } else {
          selectedRect = Optional.empty();
        }
        repaint();
      }

      @Override
      public void mouseClicked(final MouseEvent e) {
        if (newGame.contains(e.getPoint())) {
          frame.getContentPane().removeAll();
          try {
            frame.getContentPane().add(new InsertNamePanel(frame));
          } catch (IOException e1) {
            e1.printStackTrace();
          }
          frame.repaint();
        } else if (exitGame.contains(e.getPoint())) {
          frame.dispose();
        }
      }
    };
    this.addMouseListener(mouse);
    this.addMouseMotionListener(mouse);
  }

  /**
   * Paint component.
   *
   * @param graphics the graphics
   */
  @Override
  public void paintComponent(final Graphics graphics) {
    super.paintComponent(graphics);
    graphics.drawImage(img, this.getX(),
        this.getY(), this.getWidth(), this.getHeight(), null);

    GuiUtilities.colorSelectedArea(graphics, selectedRect);
    GuiUtilities.putText(graphics, "new game",
        getNewGameRect(), Color.white,
        new Font(Font.DIALOG, Font.BOLD, FONT_SIZE));

    GuiUtilities.putText(graphics, "exit game",
        getExitGameRect(), Color.white,
        new Font(Font.DIALOG, Font.BOLD, FONT_SIZE));

    graphics.dispose();
  }

  /**
   * Gets the exit game rect.
   *
   * @return the exit game rect
   */
  private Rectangle getExitGameRect() {
    return this.exitGame;
  }

  /**
   * Gets the new game rect.
   *
   * @return the new game rect
   */
  private Rectangle getNewGameRect() {
    return this.newGame;
  }
}
