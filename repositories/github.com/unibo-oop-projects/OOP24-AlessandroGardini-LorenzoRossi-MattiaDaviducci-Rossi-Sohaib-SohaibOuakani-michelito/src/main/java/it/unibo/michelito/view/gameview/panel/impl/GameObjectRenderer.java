package it.unibo.michelito.view.gameview.panel.impl;

import it.unibo.michelito.util.GameObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * Class responsible for rendering the actual game.
 */
public final class GameObjectRenderer {
    private static final double BASE_SCALE_FACTOR = 6.68;
    private static final double TRANSLATION_FACTOR = 3;
    private static final int BASE_SQUARE_DIMENSION = 40;
    private static final int BASE_BOMB_DIMENSION = 30;
    private static final int BASE_POWER_UP_DIMENSION = 20;
    private static final int BASE_RECTANGLE_WIDTH = 19;
    private static final int BASE_RECTANGLE_HEIGHT = 28;
    private static final Color WALL_COLOR = new Color(139, 69, 19);

    /**
     * Private constructor preventing instantiation.
     */
    private GameObjectRenderer() {
    }

    /**
     * Renders the game object.
     *
     * @param g the graphics object.
     * @param gameObject the game object to render.
     * @param component the panel component.
     */
    public static void render(final Graphics g, final GameObject gameObject, final JPanel component) {
        final Graphics2D g2d;
        if (g instanceof Graphics2D) {
            g2d = (Graphics2D) g;
        } else {
            throw new IllegalStateException("Graphics object is not an instance of Graphics2D");
        }

        final Dimension currentDimension = component.getSize();
        final Dimension baseDimension = component.getPreferredSize();
        final double widthScaleFactor = currentDimension.width / (double) baseDimension.width * BASE_SCALE_FACTOR;
        final double heightScaleFactor = currentDimension.height / (double) baseDimension.height * BASE_SCALE_FACTOR;

        final double currentX = (gameObject.position().x() + TRANSLATION_FACTOR) * widthScaleFactor;
        final double currentY = (gameObject.position().y() + TRANSLATION_FACTOR) * heightScaleFactor;

        // Enable anti-aliasing for smoother rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        final Rectangle2D square = createRectangle(
                currentDimension,
                baseDimension,
                currentX,
                currentY,
                BASE_SQUARE_DIMENSION,
                BASE_SQUARE_DIMENSION
        );
        final Rectangle2D rectangle = createRectangle(
                currentDimension,
                baseDimension,
                currentX,
                currentY,
                BASE_RECTANGLE_WIDTH,
                BASE_RECTANGLE_HEIGHT
        );
        final Ellipse2D bombCircle = createEllipse(
                currentDimension,
                baseDimension,
                currentX,
                currentY,
                BASE_BOMB_DIMENSION,
                BASE_BOMB_DIMENSION
        );
        final Ellipse2D powerUpCircle = createEllipse(
                currentDimension,
                baseDimension,
                currentX,
                currentY,
                BASE_POWER_UP_DIMENSION,
                BASE_POWER_UP_DIMENSION
        );

        // Draw based on the object type
        switch (gameObject.objectType()) {
            case PLAYER:
                g2d.setColor(Color.GREEN);
                g2d.fill(rectangle);
                break;
            case BOX:
                g2d.setColor(Color.CYAN);
                g2d.fill(square);
                break;
            case WALL:
                g2d.setColor(WALL_COLOR);
                g2d.fill(square);
                break;
            case ENEMY:
                g2d.setColor(Color.RED);
                g2d.fill(rectangle);
                break;
            case SPEED_POWERUP:
                g2d.setColor(Color.yellow);
                g2d.fill(powerUpCircle);
                break;
            case BOMB_LIMIT_POWERUP:
                g2d.setColor(Color.blue);
                g2d.fill(powerUpCircle);
                break;
            case BOMB_TYPE_POWERUP:
                g2d.setColor(Color.pink);
                g2d.fill(powerUpCircle);
                break;
            case DOOR:
                g2d.setColor(Color.MAGENTA);
                g2d.fill(rectangle);
                break;
            case FLAME:
                g2d.setColor(Color.ORANGE);
                g2d.fill(square);
                break;
            case BOMB:
                g2d.setColor(Color.BLACK);
                g2d.fill(bombCircle);
                break;
            case BLANK_SPACE:
                break;
            default:
                g2d.setColor(Color.BLACK);
                g2d.fill(square);
                break;
        }
    }

    private static Rectangle2D createRectangle(
            final Dimension currentDimension,
            final Dimension baseDimension,
            final double currentX,
            final double currentY,
            final int stdRectangle,
            final int stdRectangle2
    ) {
        return new Rectangle2D.Double(
                currentX - stdRectangle * currentDimension.width / (double) baseDimension.width / 2.0,
                currentY - stdRectangle2 * currentDimension.height / (double) baseDimension.height / 2.0,
                stdRectangle * currentDimension.width / (double) baseDimension.width,
                stdRectangle2 * currentDimension.height / (double) baseDimension.height
        );
    }

    private static Ellipse2D createEllipse(
            final Dimension currentDimension,
            final Dimension baseDimension,
            final double currentX,
            final double currentY,
            final int stdRectangle,
            final int stdRectangle2
    ) {
        return new Ellipse2D.Double(
                currentX - stdRectangle * currentDimension.width / (double) baseDimension.width / 2.0,
                currentY - stdRectangle2 * currentDimension.height / (double) baseDimension.height / 2.0,
                stdRectangle * currentDimension.width / (double) baseDimension.width,
                stdRectangle2 * currentDimension.height / (double) baseDimension.height
        );
    }
}
