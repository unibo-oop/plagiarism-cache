package it.tbt.model.entities.items;

/**
 * Generic Potion.
 */
public class Potion extends ItemImpl implements Consumable {
    private final int healPower;

    /**
     * Default constructor.
     * @param name
     * @param value
     * @param healPower
     */
    public Potion(final String name, final int value, final int healPower) {
        super(name, value);
        this.healPower = healPower;
    }

    /**
     * Get how much the potion heal.
     * @return int
     */
    public int getHealPower() {
        return healPower;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getName() + " - Heal: " + healPower;
    }
}
