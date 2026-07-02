package unibo.exiled.model.item;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.item.utilities.ItemType;

/**
 * This class represents a usable power-up item.
 * The use class allows the character to power up a certain statistic (for
 * example, attack).
 * The amount of power-up is determined by the powerUpValue.
 * Power-ups are not permanent, so they have a duration.
 */
public final class PowerUpItem extends ItemBase implements UsableItem {

    private final AttributeIdentifier boostedAttribute;
    private final double powerUpValue;

    /**
     * Constructs a PowerUpItem with the specified attributes.
     *
     * @param name             The name of the power-up item.
     * @param description      The description of the power-up item.
     * @param powerUpValue     The value by which the attribute is powered up.
     * @param boostedAttribute The attribute that is boosted by the power-up.
     */
    public PowerUpItem(final String name, final String description, final double powerUpValue,
                       final AttributeIdentifier boostedAttribute) {
        super(name, description, ItemType.POWERUP);
        this.boostedAttribute = boostedAttribute;
        this.powerUpValue = powerUpValue;
    }

    @Override
    public void use(final Player player) {
        player.increaseAttributeModifier(boostedAttribute, powerUpValue);
    }

    /**
     * Get the amount or effectiveness of the item.
     *
     * @return Depends on the specific type of item. For example, for a healing
     * item, it represents the amount of health restored.
     */
    @Override
    public double getAmount() {
        return this.powerUpValue;
    }

    /**
     * Gets the attribute that is boosted by the power-up.
     *
     * @return The boosted attribute.
     */
    public AttributeIdentifier getBoostedAttribute() {
        return this.boostedAttribute;
    }
}
