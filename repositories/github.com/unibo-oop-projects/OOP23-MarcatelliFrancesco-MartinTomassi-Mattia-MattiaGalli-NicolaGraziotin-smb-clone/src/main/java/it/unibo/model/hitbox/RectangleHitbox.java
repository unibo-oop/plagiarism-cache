package it.unibo.model.hitbox;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/**
 * The RectangleHitbox rapresents the implementation of the Hitbox
 * with a rectangle shape.
 */
public class RectangleHitbox implements Hitbox<Rectangle> {

    private final Rectangle hitbox;
    /**
     * Constructs a new RectangleHitbox with specified coordinates and dimensions.
     * 
     * @param x The x-coordinate of the hitbox
     * @param y The y-coordinate of the hitbox
     * @param width The width of the hitbox
     * @param height The height of the hitbox
     */
    public RectangleHitbox(final double x, final double y, final int width, final int height) {
        this.hitbox = new Rectangle((int) x, (int) y, width, height);
    }

    @Override
    public final void updatePosition(final double x, final double y) {
        this.hitbox.setLocation((int) x, (int) y);
    }

    @SuppressFBWarnings("EI_EXPOSE_REP")
    @Override
    public final Rectangle getHitbox() {
        return this.hitbox;
    }

    @Override
    public final void draw(final Graphics2D g) {
        g.setColor(Color.MAGENTA);
        g.draw(hitbox);
    }
}
