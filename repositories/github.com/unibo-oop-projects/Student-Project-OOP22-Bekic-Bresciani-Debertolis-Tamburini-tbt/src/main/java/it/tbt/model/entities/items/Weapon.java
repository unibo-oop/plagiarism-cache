package it.tbt.model.entities.items;

/**
 * Generic Weapon.
 */
public class Weapon extends ItemImpl implements Equipement {
    private final int attack;

    /**
     * Default constructor.
     * @param name
     * @param attack
     * @param value
     */
    public Weapon(final String name, final int attack, final int value) {
        super(name, value);
        this.attack = attack;
    }

    /**
     * get the attack of the weapon.
     * @return int
     */
    public final int getAttack() {
        return attack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getName() + " - Attack: " + attack;
    }
}
