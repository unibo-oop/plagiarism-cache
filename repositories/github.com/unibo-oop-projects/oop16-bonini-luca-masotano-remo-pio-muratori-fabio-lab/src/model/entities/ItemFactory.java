package model.entities;

import java.util.Optional;
import java.util.Random;

import model.hitbox.Hitbox;

/**
 * 
 * Defines all the necessary methods to constructs all the types of items.
 *
 */
public final class ItemFactory {

    private ItemFactory() {

    }

    /**
     * Constructs a new heart item.
     * 
     * @param h
     *            The Hitbox of the Item
     * @return The Item heart.
     */
    public static ItemImpl getHeartItem(final Hitbox h) {
        return new ItemImpl(h, ItemType.HEART);
    }

    /**
     * Constructs a new half heart item.
     * 
     * @param h
     *            The Hitbox of the Item
     * @return The Item half heart.
     */
    public static ItemImpl getHalfHeartItem(final Hitbox h) {
        return new ItemImpl(h, ItemType.HALF_HEART);
    }

    /**
     * Constructs a new damage up item.
     * 
     * @param h
     *            The Hitbox of the Item
     * @return The Item damage up.
     */
    public static ItemImpl getDamageUpItem(final Hitbox h) {
        return new ItemImpl(h, ItemType.DAMAGEUP);
    }

    /**
     * Constructs a new damage down item.
     * 
     * @param h
     *            The Hitbox of the Item
     * @return The Item damage down.
     */
    public static ItemImpl getDamageDownItem(final Hitbox h) {
        return new ItemImpl(h, ItemType.DAMAGEDOWN);
    }

    /**
     * Constructs a new range up item.
     * 
     * @param h
     *            The Hitbox of the Item
     * @return The Item range up.
     */
    public static ItemImpl getRangeUpItem(final Hitbox h) {
        return new ItemImpl(h, ItemType.RANGEUP);
    }

    /**
     * Constructs a new range down item.
     * 
     * @param h
     *            The Hitbox of the Item
     * @return The Item range down.
     */
    public static ItemImpl getRangeDownItem(final Hitbox h) {
        return new ItemImpl(h, ItemType.RANGEDOWN);
    }

    /**
     * Constructs a new fire rate up item.
     * 
     * @param h
     *            The Hitbox of the Item
     * @return The Item fire rate up.
     */
    public static ItemImpl getFireRateUpItem(final Hitbox h) {
        return new ItemImpl(h, ItemType.FIREUP);
    }

    /**
     * Constructs a new fire rate down item.
     * 
     * @param h
     *            The Hitbox of the Item.
     * @return The Item fire rate down.
     */
    public static ItemImpl getFireRateDownItem(final Hitbox h) {
        return new ItemImpl(h, ItemType.FIREDOWN);
    }

    /**
     * Computes if a random item should be dropped based on a probability.
     * @param h
     *            The Hitbox where the item will spawn
     * @return An Optional which contains the item dropped. An empty Optional is returned if
     *         no items are dropped.
     */
    public static Optional<ItemImpl> randomItemDrop(final Hitbox h) {
        final Random r = new Random();
        final int prob = r.nextInt(5);

        if (prob > 2) {
            return Optional.empty();
        }

        final ItemType it = ItemType.getRandomItem();
        switch (it) {
        case HEART:
            return Optional.of(getHeartItem(h));
        case HALF_HEART:
            return Optional.of(getHalfHeartItem(h));
        case DAMAGEDOWN:
            return Optional.of(getDamageDownItem(h));
        case DAMAGEUP:
            return Optional.of(getDamageUpItem(h));
        case FIREDOWN:
            return Optional.of(getFireRateDownItem(h));
        case FIREUP:
            return Optional.of(getFireRateUpItem(h));
        case RANGEDOWN:
            return Optional.of(getRangeDownItem(h));
        case RANGEUP:
            return Optional.of(getRangeUpItem(h));
        default:
            return Optional.empty();
        }
    }

}
