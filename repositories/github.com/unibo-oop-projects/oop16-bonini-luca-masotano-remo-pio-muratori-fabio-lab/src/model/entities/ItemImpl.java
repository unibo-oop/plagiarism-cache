package model.entities;

import model.hitbox.Hitbox;
import model.hitbox.HitboxRectangle;

/**
 * 
 * Defines all the items of the game.
 *
 */
public class ItemImpl implements Item {

    /**
     * A unique serial version identifier
     * 
     * @see Serializable#serialVersionUID
     */
    private static final long serialVersionUID = 4717550341812112444L;
    private static final double WIDTH = 40;
    private static final double HEIGHT = 40;
    private final HitboxRectangle h;
    private final ItemType i;

    /**
     * Constructs a new Item.
     * 
     * @param h
     *            The Hitbox representing the position of this item.
     * @param item
     *            Defines the ItemType value which is representing this item.
     */
    public ItemImpl(final Hitbox h, final ItemType item) {
        this.h = new HitboxRectangle(h.getX(), h.getY(), WIDTH, HEIGHT);
        this.i = item;
    }

    @Override
    public double getValue() {
        return this.i.getValue();
    }

    @Override
    public ItemType getItemType() {
        return this.i;
    }

    @Override
    public HitboxRectangle getHitbox() {
        return this.h;
    }

}
