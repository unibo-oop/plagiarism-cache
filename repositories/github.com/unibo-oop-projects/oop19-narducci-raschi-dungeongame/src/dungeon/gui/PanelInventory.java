package dungeon.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dungeon.GuiUtilities;
import dungeon.character.player.InventoryImpl;
import dungeon.logic.GameLogic;



//You do not silence checkstyle like this. MagicNumber OFF

/**
 * The Class PanelInventory.
 */
public final class PanelInventory extends JPanel {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -7556473702201048061L;

  /** The Constant RECT_HEIGHT. */
  private static final int RECT_HEIGHT = 50;

  /** The Constant BIG_RECT_WIDTH. */
  private static final int BIG_RECT_WIDTH = GuiUtilities.SIZE * 14;

  /** The Constant SMALL_RECT_WIDTH. */
  private static final int SMALL_RECT_WIDTH = 30;

  /** The Constant space between .
   * the first x axis between 2 rectangles */
  private static final int RECT_SPACE_Y = 70;

  /** The Constant RECT_COORD. */
  private static final int RECT_COORD = (int) (GuiUtilities.SIZE * 1.5);

  /** The selected. */
  private Optional<Rectangle> selected;

  /** The rectangles. */
  private List<Rectangle> rectangles;

  /** The game logic. */
  private GameLogic gameLogic;

  /**
   * Instantiates a new panel inventory.
   *
   * @param gameLogic the game logic
   */
  public PanelInventory(final GameLogic gameLogic) {
    super();
    selected = Optional.empty();
    rectangles = new ArrayList<>();
    this.setVisible(false);
    this.gameLogic = gameLogic;
    for (int i = 0; i < 4; i++) {
      Rectangle rect = new Rectangle(RECT_COORD, RECT_COORD + i * RECT_SPACE_Y,
          BIG_RECT_WIDTH, RECT_HEIGHT);
      rectangles.add(rect);
    }
    final MouseAdapter mouse = new MouseAdapter() {

      @Override
      public void mouseMoved(final MouseEvent e) {
        for (int i = 0; i < 4; i++) {
          if (rectangles.get(i).contains(e.getPoint())) {
            selected = Optional.of(rectangles.get(i));
          }
        }
        repaint();
      }

      @Override
      public void mouseClicked(final MouseEvent e) {
        for (int i = 0; i < 4; i++) {
          if (rectangles.get(i).contains(e.getPoint()) 
              && SwingUtilities.isRightMouseButton(e)) {
            gameLogic.removeItem(i);
          } else if (rectangles.get(i).contains(e.getPoint()) 
              && !SwingUtilities.isRightMouseButton(e)) {
            gameLogic.takeItem(i);
          }
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
    graphics.setColor(Color.BLACK);
    graphics.fillRect(this.getX(),
        this.getY(),
        this.getWidth(), this.getHeight());
    graphics.setColor(Color.WHITE);
    graphics.drawRoundRect(5, 5, this.getWidth() - 10,
        this.getHeight() - 10, 10, 10);
    graphics.setFont(new Font(Font.DIALOG, Font.BOLD, GuiUtilities.SIZE));
    graphics.drawString("Inventory", GuiUtilities.SIZE, GuiUtilities.SIZE);

    for (int i = 0; i < 4; i++) {
      graphics.drawRect(rectangles.get(i).x, rectangles.get(i).y,
          rectangles.get(i).width, rectangles.get(i).height);
      graphics.drawRect(rectangles.get(i).x, rectangles.get(i).y,
          SMALL_RECT_WIDTH, rectangles.get(i).height);
    }

    for (int i = 0; i < InventoryImpl.BAG_SIZE; i++) {
      Optional<URL> imageUrl = gameLogic.getUrl(i);
      BufferedImage image = null;
      if (imageUrl.isPresent()) {
        try {
          image = ImageIO.read(imageUrl.get());
        } catch (IOException e) {
          e.printStackTrace();
        }
        graphics.drawImage(image, RECT_COORD, rectangles.get(i).y, 
            SMALL_RECT_WIDTH, RECT_HEIGHT, Color.WHITE, null);
        Rectangle rect = new Rectangle(
            RECT_COORD + SMALL_RECT_WIDTH, rectangles.get(i).y,
            BIG_RECT_WIDTH - SMALL_RECT_WIDTH,
            rectangles.get(i).height);
        GuiUtilities.putText(graphics, gameLogic.getItemDescription(i),
          rect, Color.WHITE, new Font(Font.DIALOG, Font.BOLD, 15));
      }
    }
    graphics.setColor(Color.BLUE);
    if (selected.isPresent()) {
      graphics.drawRect(selected.get().x, selected.get().y,
          selected.get().width, selected.get().height);
    }
    graphics.dispose();

  }
}
