package view.graphics.graphics_swing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;
import view.graphics_util.Scaler;

/**
 * Swing implementation of {@link GraphicsMunition} that renders munition
 * entities.
 * 
 * <p>
 * This class uses {@link Graphics2D} to draw a munition image on the screen,
 * scaling its position according to the given {@link Scaler}.
 * </p>
 */
public class SwingGraphicsMunition implements GraphicsMunition {

    private Graphics2D g2d;
    private Scaler viewScale;

    /**
     * Constructs a new {@code SwingGraphicsMunition} with the given graphics
     * context and scaler.
     *
     * @param g2d       the {@link Graphics2D} used for drawing
     * @param viewScale the {@link Scaler} used to scale coordinates
     */
    public SwingGraphicsMunition(final Graphics2D g2d, final Scaler viewScale) {
        this.g2d = g2d;
        this.viewScale = viewScale;
    }

    /**
     * Draws the munition on the screen at the scaled position.
     * 
     * <p>
     * The munition is drawn as a 6x6 pixels image at its current position,
     * scaled according to the view's scaler.
     * </p>
     *
     * @param mun   the {@link Munition} entity to draw
     * @param image the {@link BufferedImage} representing the munition's sprite
     */
    @Override
    public void drawMunition(final Munition mun, final BufferedImage image) {

        int scaleSurPosX = viewScale.scaleX(mun.getCurrentPos());
        int scaleSurPosY = viewScale.getScaledHeight() - viewScale.scaleY(mun.getCurrentPos());

        int scaleSurHeight = (int) Math.round(mun.getWidth() * viewScale.getRatioY());

        g2d.drawImage(image, scaleSurPosX, scaleSurPosY - scaleSurHeight, 6, 6, null);

    }

}
