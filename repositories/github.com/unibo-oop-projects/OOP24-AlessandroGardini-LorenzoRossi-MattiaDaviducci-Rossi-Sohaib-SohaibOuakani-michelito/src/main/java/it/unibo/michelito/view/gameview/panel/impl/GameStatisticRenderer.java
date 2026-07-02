package it.unibo.michelito.view.gameview.panel.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Class responsible for rendering the game statistics.
 */
public final class GameStatisticRenderer {
    /**
     * Offset used in rendering the game statistics for the left.
     */
    public static final int OFFSET_FROM_VERTICAL_LEFT_CORNER = 10;
    /**
     * Offset used in rendering the game statistics for the right.
     */
    public static final int OFFSET_FROM_VERTICAL_RIGHT_CORNER = 90;
    /**
     * Font size for the game statistics.
     */
    public static final int BASE_FONT_SIZE = 18;
    /**
     * Offset used in rendering the game statistics for the top.
     */
    public static final int OFFSET_FROM_HORIZONTAL_CORNER = 25;

    /**
     * Private constructor preventing instantiation.
     */
    private GameStatisticRenderer() {
    }

    /**
     * Renders the game statistics.
     *
     * @param g the graphics object.
     * @param component the panel component.
     * @param lives the number of lives.
     * @param levelNumber the level number.
     */
    public static void render(final Graphics g, final JPanel component, final int lives, final int levelNumber) {
        final Graphics2D g2d;
        if (g instanceof Graphics2D) {
            g2d = (Graphics2D) g;
        } else {
            throw new IllegalStateException("Graphics object is not an instance of Graphics2D");
        }

        // Set rendering hints for better text quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final var baseDimension = component.getPreferredSize();
        final float widthScale = component.getWidth() / (1.0f * baseDimension.width);
        final float heightScale = component.getHeight() / (1.0f * baseDimension.height);

        g2d.setFont(new Font("Serif", Font.BOLD, Math.round(BASE_FONT_SIZE * widthScale)));
        g2d.setColor(Color.RED);

        g2d.drawString(
                "LIVES: " + "❤ ".repeat(lives),
                OFFSET_FROM_VERTICAL_LEFT_CORNER * widthScale,
                OFFSET_FROM_HORIZONTAL_CORNER * heightScale);
        g2d.drawString("LEVEL: " + levelNumber,
                (baseDimension.width - OFFSET_FROM_VERTICAL_RIGHT_CORNER) * widthScale,
                OFFSET_FROM_HORIZONTAL_CORNER * heightScale);
    }
}
