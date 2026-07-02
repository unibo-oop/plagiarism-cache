package view.graphics.graphics_swing;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.entities.survivor.Survivor;
import view.graphics.GraphicsSurvivor;
import view.graphics_util.Scaler;

/**
 * Implementation of {@link GraphicsSurvivor} using Java Swing's Graphics2D.
 * 
 * <p>
 * This class handles rendering a {@link Survivor} entity on the screen,
 * including drawing the survivor's image scaled to the view and a health bar
 * displayed above the survivor.
 * </p>
 * 
 * <p>
 * Coordinates and sizes are scaled using a {@link Scaler} to adapt the
 * survivor's real-world positions to screen pixels.
 * </p>
 */
public class SwingGraphicsSurvivor implements GraphicsSurvivor {

    private Graphics2D g2d;
    private Scaler viewScale;

    /**
     * Constructs a new {@code SwingGraphicsSurvivor} with the specified graphics
     * context and scaler.
     * 
     * @param g2d       the {@link Graphics2D} object used for rendering
     * @param viewScale the {@link Scaler} responsible for coordinate and size
     *                  scaling
     */
    public SwingGraphicsSurvivor(final Graphics2D g2d, final Scaler viewScale) {
        this.g2d = g2d;
        this.viewScale = viewScale;
    }

    /**
     * Draws the survivor on the screen.
     * 
     * <p>
     * This method scales the survivor's position and dimensions according
     * to the {@code Scaler}, draws the survivor's image, and then draws the
     * health bar above the survivor.
     * </p>
     * 
     * @param sur   the {@link Survivor} entity to be drawn
     * @param image the {@link BufferedImage} representing the survivor's sprite or
     *              graphic
     */
    @Override
    public void drawSurvivor(final Survivor sur, final BufferedImage image) {

        int scaleSurPosX = viewScale.scaleX(sur.getCurrentPos());
        int scaleSurPosY = viewScale.getScaledHeight() - viewScale.scaleY(sur.getCurrentPos());

        int scaleSurWidth = (int) Math.round(sur.getWidth() * viewScale.getRatioX());
        int scaleSurHeight = (int) Math.round(sur.getHeight() * viewScale.getRatioY());

        // Draw the Survivor
        g2d.drawImage(image, scaleSurPosX, scaleSurPosY - scaleSurHeight, scaleSurWidth, scaleSurHeight, null);
        drawSurvivorHealth(sur, scaleSurPosX, scaleSurPosY, scaleSurHeight, scaleSurWidth);
    }

    /**
     * Draws the health bar of the survivor above the survivor's image.
     * 
     * <p>
     * The health bar changes color depending on the current health ratio:
     * green for > 50%, yellow for 25-50%, and red for less than 25%.
     * </p>
     * 
     * @param sur            the survivor whose health bar is drawn
     * @param scaleSurPosX   the scaled X position of the survivor
     * @param scaleSurPosY   the scaled Y position of the survivor (bottom edge)
     * @param scaleSurHeight the scaled height of the survivor
     * @param scaleSurWidth  the scaled width of the survivor
     */
    private void drawSurvivorHealth(final Survivor sur, final int scaleSurPosX, final int scaleSurPosY,
            final int scaleSurHeight, final int scaleSurWidth) {

        // Dimension of the rectangle (life bar)
        int rectWidth = scaleSurWidth;
        int rectHeight = 6; // height rect

        // Position of the rectangle
        int rectX = scaleSurPosX;
        int rectY = scaleSurPosY - scaleSurHeight - rectHeight - 5; // 5 pixel up zombie head

        // Calculate actual width according to Survivor life
        final int maxLife = sur.getMaxSurvivorHealth();
        int currentLife = sur.getLive();
        int currentWidth = (int) ((double) currentLife / maxLife * rectWidth);

        // Draw background (black) rectangle
        g2d.setColor(Color.BLACK);
        g2d.fillRect(rectX, rectY, rectWidth, rectHeight);

        // Decide life bar color based on health ratio
        float healthRatio = (float) currentLife / maxLife;
        if (healthRatio > 0.5) {
            g2d.setColor(Color.GREEN);
        } else if (healthRatio > 0.25) {
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.RED);
        }

        // Draw life bar
        g2d.fillRect(rectX, rectY, currentWidth, rectHeight);
    }

    /**
     * (Optional) Draws a bounding box around the survivor for debugging purposes.
     * 
     * <p>
     * This method is currently unused but can be enabled to visualize the
     * survivor's collision box.
     * </p>
     * 
     * @param sur            the survivor to draw the bounding box for
     * @param scaleSurHeight the scaled height of the survivor
     * @param scaleSurWidth  the scaled width of the survivor
     */
    @SuppressWarnings("unused")
    private void drawBoundingBox(final Survivor sur, final int scaleSurHeight, final int scaleSurWidth) {
        int scaleBboxUx = viewScale.scaleX(sur.getBBox().getULcorner());
        int scaleBboxUy = viewScale.getScaledHeight() - viewScale.scaleY(sur.getBBox().getULcorner());

        // Draw the Survivor BoundingBox
        g2d.setColor(Color.blue);
        g2d.drawRect(scaleBboxUx, scaleBboxUy, scaleSurWidth, scaleSurHeight);
    }
}
