package unibo.exiled.model.item;

import unibo.exiled.model.item.utilities.ItemType;

import java.util.Objects;

/**
 * This abstract class implements the Item interface by providing
 * a common core implementation for items in the game.
 * Objects can extend this class to inherit this base implementation and add
 * specific features.
 * The class has a constructor that requires the name and description of the
 * object, the type it can
 * be Healing (curative), PowerUp (strengthening), or Standard (unusable object
 * e.g., crystals), and if it is
 * a usable item, the value of what it upgrades or heals.
 * Examples of items that can extend this class include healing items,
 * power-up items, etc.
 */
public abstract class ItemBase implements Item {
    private final String name;
    private final String description;
    private final ItemType itemType;

    /**
     * Constructs an ItemBase with the specified attributes.
     *
     * @param name        The name of the item.
     * @param description The description of the item.
     * @param itemType    The type of the item.
     */
    public ItemBase(final String name, final String description, final ItemType itemType) {
        this.name = name;
        this.description = description;
        this.itemType = itemType;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the item.
     *
     * @return the description of the item.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Compares this item with another object for equality.
     * 
     * @param obj The object to compare with this item.
     * @return true if the specified object is equal to this item,
     *         false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        return name.equals(other.getName());
    }

    /**
     * Returns a hash code value for this item.
     * 
     * @return A hash code value for this item.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    /**
     * Return the type of the item.
     *
     * @return the type of the item.
     */
    @Override
    public ItemType getType() {
        return this.itemType;
    }
}
