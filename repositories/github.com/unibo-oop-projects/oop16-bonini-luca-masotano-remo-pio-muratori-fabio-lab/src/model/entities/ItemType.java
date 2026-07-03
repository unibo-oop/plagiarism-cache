package model.entities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * Defines all the types of items that modify a certain property of a character.
 *
 */
public enum ItemType {

    /**
     * The full heart item. It modify the life of a character.
     */
    HEART(2, Property.LIFE),

    /**
     * The half heart item. It modify the life of a character.
     */
    HALF_HEART(1, Property.LIFE),

    /**
     * The range up items. It modify the range of the bullets shot by a character.
     */
    RANGEUP(40, Property.RANGE),

    /**
     * The range down item. It modify the range of the bullets shot by a character.
     */
    RANGEDOWN(-40, Property.RANGE),

    /**
     * The damage up item. It modify the damage of the bullets shot by a character.
     */
    DAMAGEUP(1, Property.DAMAGE),

    /**
     * The damage down item. It modify the damage of the bullets shot by a character.
     */
    DAMAGEDOWN(1, Property.DAMAGE),

    /**
     * The fire rate up item. It modify the fire rate of a character.
     */
    FIREUP(-3, Property.FIRERATE),

    /**
     * The fire rate down item. It modify the fire rate of a character.
     */
    FIREDOWN(3, Property.FIRERATE);

    private static List<ItemType> l = Arrays.asList(values());
    private double value;
    private Property p;

    ItemType(final double value, final Property p) {
        this.value = value;
        this.p = p;
    }

    /**
     * Get the value contained in this items.
     * 
     * @return The value.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Get the property modified by this item.
     * 
     * @return The property.
     */
    public Property getProperty() {
        return this.p;
    }

    /**
     * Generate a random item's type.
     * 
     * @return A random item type.
     */
    public static ItemType getRandomItem() {
        Collections.shuffle(l);
        return l.get(0);
    }

}
