package it.unibo.model.entity.player;

import it.unibo.commons.Constants;
import it.unibo.model.entity.EntityImpl;
import it.unibo.model.hitbox.RectangleHitbox;

/**
 * This class represents a specific implementation of the {@link EntityImpl} class for the MeatBoy entity.
 * It extends {@link EntityImpl} and specifies a hitbox of type {@link RectangleHitbox}, thereby implementing
 * the {@link MeatBoy} interface.
 */
public class MeatBoyImpl extends EntityImpl<RectangleHitbox> implements MeatBoy {

    private double speedMul;
    /**
     * Represents the default speed value for an entity.
     * This constant is typically used when no specific speed multiplier is set.
     */
    public static final double SPEED = 3 * Constants.SCALE_PROPORTION;

    /**
     * Represents the maximum jump height for an entity.
     * This constant defines the maximum vertical distance an entity can jump.
     */
    public static final double MAX_JUMP_HEIGHT = 100 * Constants.SCALE_PROPORTION;

    /**
     * Represents the falling speed for an entity.
     * This constant defines the speed at which an entity falls when not supported by a surface.
     */
    public static final double FALLING_SPEED = 5 * Constants.SCALE_PROPORTION;

    /**
     * Represents the jump speed for an entity.
     * This constant defines the initial upward speed when an entity jumps.
     */
    public static final double JUMP_SPEED = 10 * Constants.SCALE_PROPORTION;

    /**
     * Constructs a MeatBoyImpl object with the specified coordinates.
     * 
     * @param x The x-coordinate of the MeatBoy.
     * @param y The y-coordinate of the MeatBoy.
     */
    public MeatBoyImpl(final double x, final double y) {
        super(x, y, 
            new RectangleHitbox(
                x, 
                y, 
                (int) (Constants.TILE_SIZE * Constants.SCALE_PROPORTION), 
                (int) (Constants.TILE_SIZE * Constants.SCALE_PROPORTION))); 
        this.speedMul = 1;
    }

    @Override
    public final double getSpeedMul() {
        return this.speedMul;
    }

    @Override
    public final void setSpeedMul(final double speedMul) {
        this.speedMul = speedMul;
    }
}
