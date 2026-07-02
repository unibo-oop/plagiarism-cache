package view.graphics.graphics_swing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import model.level.types.Level;
import view.graphics.GraphicsLevel;
import view.graphics_util.Scaler;

/**
 * Swing implementation for rendering the game level.
 * Draws the bounding box of the level and the tile grid.
 */
public class SwingGraphicsLevel implements GraphicsLevel {

    private final static int WIDTH_WEAPON_IMAGE = 50;
    private final static int HEIGHT__WEAPON_IMAGE = 50;
    private Graphics2D g2d;
    private Scaler viewScale;

    /**
     * Constructor with graphics context and scaler.
     *
     * @param g2d       the Graphics2D object to draw on
     * @param viewScale the scaler object that converts game coordinates to view
     *                  coordinates
     */
    public SwingGraphicsLevel(final Graphics2D g2d, final Scaler viewScale) {
        this.g2d = g2d;
        this.viewScale = viewScale;
    }

    /**
     * Draws the level based on the model data and tile images.
     *
     * @param lvl        the level to be drawn
     * @param allImage   the matrix of tile images to render
     * @param otherImage the image to render
     */
    @Override
    public void drawLevel(final Level lvl, final List<List<BufferedImage>> allImage, final BufferedImage otherImage) {
        // Calculate total level size in pixels
        int totalWidth_px = (int) Math.round(lvl.getLevelWidth() * viewScale.getRatioX());
        int totalHeight_px = (int) Math.round(lvl.getLevelHeight() * viewScale.getRatioY());

        drawTiles(totalWidth_px, totalHeight_px, allImage, g2d);

        // Draw otherImage at bottom-right if present
        if (otherImage != null) {
            drawSurvivorWeapon(totalWidth_px, otherImage, g2d);
        }

    }

    /**
     * Draws the tile grid of the level.
     * <p>
     * Tiles are drawn on a fixed 26x14 grid scaled to fit the total width and
     * height.
     * The method handles correction for the last row and column to avoid uncovered
     * pixels.
     * </p>
     *
     * @param totalWidth_px  the total width of the level in pixels
     * @param totalHeight_px the total height of the level in pixels
     * @param allImage       the matrix of tile images to render
     * @param g2d            the Graphics2D context on which to draw
     */

    private void drawTiles(final int totalWidth_px, final int totalHeight_px, final List<List<BufferedImage>> allImage,
            Graphics2D g2d) {
        // Tile dimensions based on fixed 26x14 grid
        int tileW_px = totalWidth_px / 26;
        int tileH_px = totalHeight_px / 14;

        // Iterate over all rows and columns of the tile image matrix
        for (int j = 0; j < allImage.size(); j++) {
            List<BufferedImage> row = allImage.get(j);
            for (int i = 0; i < row.size(); i++) {
                BufferedImage tileImg = row.get(i);

                int x = tileW_px * i;
                int y = tileH_px * j;

                // Correction for the last column/row to avoid uncovered pixels
                int drawWidth = (i == 25) ? totalWidth_px - tileW_px * i : tileW_px;
                int drawHeight = (j == 13) ? totalHeight_px - tileH_px * j : tileH_px;

                if (tileImg != null) {
                    g2d.drawImage(tileImg, x, y, drawWidth, drawHeight, null);
                }
            }
        }
    }

    /**
     * Draws an additional image centered horizontally at the top of the level.
     * <p>
     * The image is drawn with a fixed size of 50x50 pixels.
     * </p>
     *
     * @param totalWidth_px the total width of the level in pixels
     * @param otherImage    the image to be drawn
     * @param g2d           the Graphics2D context on which to draw
     */
    private void drawSurvivorWeapon(final int totalWidth_px, final BufferedImage otherImage, Graphics2D g2d) {
        // Calculate horizontal center position
        int x = (totalWidth_px - otherImage.getWidth()) / 2;
        int y = 0;

        // Draw otherImage at the top center
        g2d.drawImage(otherImage, x, y,WIDTH_WEAPON_IMAGE, HEIGHT__WEAPON_IMAGE, null);
    }

}
