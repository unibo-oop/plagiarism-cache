package dungeon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.util.Optional;

/**
 * The Class GuiUtilities.
 */
public final class GuiUtilities {

  /**
   * Instantiates a new gui utilities.
   */
  private GuiUtilities() {

  }

  /** The Constant userDimension. */
  public static final Dimension USER_DIMENSION = 
      Toolkit.getDefaultToolkit().getScreenSize();

  /** The Constant SIZE. */
  public static final int SIZE = 32;

  /**
   * Color selected area.
   *
   * @param graphics the graphics
   * @param selected the selected
   */
  public static void colorSelectedArea(final Graphics graphics,
      final Optional<Rectangle> selected) { 
    if (selected.isPresent()) {
      graphics.setColor(Color.WHITE);
      graphics.drawRect(selected.get().x, selected.get().y,
          selected.get().width, selected.get().height);
      graphics.setColor(Color.GREEN);
      graphics.fillRect(selected.get().x, selected.get().y,
          selected.get().width, selected.get().height);
    }
  }

  /**
   * Put text.
   *
   * @param graphics the graphics
   * @param text the text
   * @param rect the rect
   * @param color the color
   * @param font the font
   */
  public static void putText(final Graphics graphics,
      final String text, final Rectangle2D rect,
      final Color color, final Font font) {

    FontMetrics metrix = graphics.getFontMetrics();
    graphics.setColor(color);
    graphics.setFont(font);
    graphics.drawString(text, (int)
        (rect.getX() + ((rect.getWidth() - metrix.stringWidth(text)) / 2)),
        (int) (rect.getY() + (
            (rect.getHeight() - metrix.getHeight()) / 2) + metrix.getAscent()));
  }
}
