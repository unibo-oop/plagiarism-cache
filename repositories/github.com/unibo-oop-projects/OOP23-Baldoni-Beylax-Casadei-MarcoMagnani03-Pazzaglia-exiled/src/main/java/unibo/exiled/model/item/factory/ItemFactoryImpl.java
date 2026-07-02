package unibo.exiled.model.item.factory;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.HealingItem;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.PowerUpItem;
import unibo.exiled.model.item.UnUsableItem;

/**
 * Implementation of the ItemFactory interface.
 * This factory provides methods to create different types of items.
 */
public class ItemFactoryImpl implements ItemFactory {

    /**
     * Creates a healing item with the specified name, description, and healing
     * value.
     *
     * @param name         The name of the healing item.
     * @param description  The description of the healing item.
     * @param healingValue The amount of healing provided by the item.
     * @return A new Item representing a healing item.
     */
    @Override
    public Item createHealingItem(final String name, final String description, final double healingValue) {
        return new HealingItem(name, description, healingValue);
    }

    /**
     * Creates a power-up item with the specified name, description, power-up value,
     * and duration.
     *
     * @param name             The name of the power-up item.
     * @param description      The description of the power-up item.
     * @param powerUpValue     The value representing the power-up effect of the
     *                         item.
     * @param boostedAttribute The attribute that the power up infect.
     * @return A new Item representing a power-up item.
     */
    @Override
    public Item createPowerUpItem(final String name, final String description, final double powerUpValue,
            final AttributeIdentifier boostedAttribute) {
        return new PowerUpItem(name, description, powerUpValue, boostedAttribute);
    }

    /**
     * Creates an unusable item with the specified name and description.
     *
     * @param name        The name of the unusable item.
     * @param description The description of the unusable item.
     * @return A new Item representing an unusable item.
     */
    @Override
    public Item createUnUsableItem(final String name, final String description) {
        return new UnUsableItem(name, description);
    }
}
