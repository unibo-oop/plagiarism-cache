package it.unibo.plantsfarm.model.garden;

import java.awt.Rectangle;

/**
 * Represents a buff in the game, which provides xp to the player's items when collected.
 */
public final class Buff {

    /**
     * Enum representing the types of buffs available in the game.
     */
    public enum Type { OMNI_BUFF }

    private final Rectangle area;
    private final Type type;

    /**
     * Creates a new Buff with the specified position and randomly assigned type.
     *
     * @param area The position of the buff as a Rectangle.
     */
    public Buff(final Rectangle area) {
        this.area = new Rectangle(area);
        this.type = Type.OMNI_BUFF;
    }

    /**
     * Gets the type of the buff.
     *
     * @return The type of the buff.
     */
    public Type getBuffType() {
        return this.type;
    }

    /**
     * Gets the position of the buff.
     *
     * @return The position of the buff as a Rectangle.
     */
    public Rectangle getBuffPosition() {
        return new Rectangle(this.area);
    }
}
