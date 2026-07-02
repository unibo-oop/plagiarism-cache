package unibo.exiled.model.item;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.CombinedAttribute;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.attributes.AdditiveAttributeImpl;
import unibo.exiled.model.item.utilities.ItemType;

/**
 * This class represents a usable healing item,
 * the use class allows the character to recover a certain amount of missing
 * life,
 * the amount of healing is determined by the healing value.
 */
public final class HealingItem extends ItemBase implements UsableItem {
    private final double healingAmount;

    /**
     * The constructor of the healing item.
     *
     * @param name          The name of the item.
     * @param description   The description of the item.
     * @param healingAmount The amount the item heals.
     */
    public HealingItem(final String name, final String description, final double healingAmount) {
        super(name, description, ItemType.HEALTH);
        this.healingAmount = healingAmount;
    }

    @Override
    public void use(final Player player) {
        final double healthCap = ((AdditiveAttributeImpl) player.getAttributes().get(AttributeIdentifier.HEALTHCAP)).value();
        final double health = ((CombinedAttribute) player.getAttributes().get(AttributeIdentifier.HEALTH)).value();

        if (health + healingAmount > healthCap) {
            player.increaseAttributeValue(AttributeIdentifier.HEALTH, healthCap - health);
        } else {
            player.increaseAttributeValue(AttributeIdentifier.HEALTH, healingAmount);
        }
    }

    @Override
    public double getAmount() {
        return this.healingAmount;
    }
}
