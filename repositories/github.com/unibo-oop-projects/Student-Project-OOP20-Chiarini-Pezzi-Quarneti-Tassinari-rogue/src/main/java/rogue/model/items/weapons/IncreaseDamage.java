package rogue.model.items.weapons;

/**
 * A decorator to increase the weapon damage.
 */
public final class IncreaseDamage extends WeaponDecorator {

    private static final int ADDITIONAL_DAMAGE = 3;

    public IncreaseDamage(final Weapon weapon) {
        super(weapon);
    }

    @Override
    public int getDamage(final Use use) {
        return super.getDamage(use) + ADDITIONAL_DAMAGE;
    }

    @Override
    public String toString() {
        return super.toString() + " increased damage";
    }

}
